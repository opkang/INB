package ApiMonitoring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class LoggingAspectTest {
    @Mock
    KafkaTemplate<String, String> kafkaTemplate;
    @Mock
    Logger logger;
    @Mock
    JdbcTemplate jdbcTemplate;
    @InjectMocks
    LoggingAspect loggingAspect;
    @Mock
    ProceedingJoinPoint proceedingJoinPoint;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testProfileAllMethods() throws Throwable {
        Object result = loggingAspect.profileAllMethods(proceedingJoinPoint);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testConnect() throws SQLException {
        Connection result = loggingAspect.connect();
        Assertions.assertEquals(loggingAspect.connect(), result);
    }
}

