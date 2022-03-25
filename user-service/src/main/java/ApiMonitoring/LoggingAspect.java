package ApiMonitoring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;


import java.sql.*;


@Aspect
@Component
public class LoggingAspect {

    private static final String url = "jdbc:postgresql://localhost:5432/dvdRental";
    private static final String username = "postgres";
    private static final String password = "sudo";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(Company.class);
    // Minimum time to track (in Seconds).
    // If the execution time is above this, API Call will be logged.
    private static final int MIN_EXECUTION_TIME_TO_TRACK = 0;

    // Your Application Name
    private static final String APPLICATION_NAME = "SAMPLE";

    // Your Database in Influx Database
    private final String POSTGRES_DATABASE = "dvdRental";

    // Replace the IP & Port with your Influx DB IP & Port
    private final String POSTGRES_URL = "http://localhost:5432/write?db=" + POSTGRES_DATABASE;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Method to Send the Data to Influx DB
     * @param className - ClassName being Tracked
     * @param methodName - MethodName being Tracked
     * @param timeInSec - Method Execution Time (Response Time)
     */
    private void pushDataToInfluxDB(String className, String methodName,
                                    double timeInSec) {

        new Thread(() -> {
            String classMethod = className + "." + methodName;
            String data = "od-execution-time,host=" + APPLICATION_NAME +
                    ",classMethod=" + classMethod +
                    " value=" + timeInSec;

            try {



                // time_taken,application_name,classMethod
                String sql = "INSERT INTO record (time_taken,application_name,class_method) VALUES (?,?,?)";


                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                pstmt.setDouble(1,timeInSec);
                pstmt.setString(2,APPLICATION_NAME);
                pstmt.setString(3,classMethod);


                int rows = pstmt.executeUpdate();
                //int rows = jdbcTemplate.update(sql);
                if (rows > 0) {
                    logger.debug("New record added");
                }



            } catch (Exception e) {
                logger.error(e.getMessage());
            }

        }).start();
    }

    // Please use the package which has all of your controllers
    @Around("execution(* Hitachi..*(..))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        MethodSignature methodSignature =
                (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        logger.debug("Class name :" + className);
        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        if (stopWatch.getTotalTimeSeconds() > MIN_EXECUTION_TIME_TO_TRACK) {
            pushDataToInfluxDB(className, methodName, stopWatch.getTotalTimeSeconds());
        }
        logger.info(String.format("#### -> Producing message -> %s",className));
        this.kafkaTemplate.send(className,methodName);
        return result;
    }
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}