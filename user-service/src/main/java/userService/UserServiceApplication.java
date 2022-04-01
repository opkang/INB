package userService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication
public class UserServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
