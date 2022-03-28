package userService;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import userService.entity.User;

@Slf4j
@Aspect
@Component
public class UserServiceAspect {

    //user(userID,firstName,lastName,email)
    @Before("execution(* userService.controller.UserController.saveUser(User)), && args(user)")
    public void beforeAddingUser(JoinPoint jp,User user){
        log.info("User: " + user);
        log.info("Before adding");
    }



}
