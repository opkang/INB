package userService;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import userService.entity.User;

@Slf4j
@Aspect
@Component
public class UserServiceAspect {
    @AfterThrowing(value = "execution(* userService.*.*.*(..))", throwing = "error")
    public void afterThrowingAdvice(JoinPoint jp, Throwable error){
        System.out.println("Method Signature: "  + jp.getSignature());
        System.out.println("Exception: "+error);
    }

    @Around("execution(* userService.controller.UserController.saveUser(..)) && args(user)")
    public User beforeAddingUser(ProceedingJoinPoint pjp, User user){
        try {
            if(validateUser(user)){
                log.info("User: " + user);
                log.info("Before adding");
                pjp.proceed();
            }
        }catch (Throwable e) {
            log.error("Adding user Failed...");
            log.info("User: " + user);
            log.error("Reason : " + e.getMessage());
            log.error("Exception Detail : " + e.getLocalizedMessage());}
        return user;
    }

    public boolean validateUser(User user){
        return !(user.getFirstName() == null || user.getFirstName().isEmpty() &&
                user.getLastName() == null || user.getLastName().isEmpty() &&
                user.getEmail() == null || user.getEmail().isEmpty());
    }


}
