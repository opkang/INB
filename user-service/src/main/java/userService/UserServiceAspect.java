package userService;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;

@Slf4j
@Aspect
@Component
public class UserServiceAspect {

    @Before("execution(* userService.controller.UserController.test())")
    public void beforeAddingUser(){
        log.info("Before test");
    }

}
