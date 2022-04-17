package userService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import userService.entity.User;
import userService.service.UserService;

import java.util.List;

class UserServiceAspectTest {

    @Mock
    Logger log;
    @InjectMocks
    UserServiceAspect userServiceAspect;
    @Mock
    ProceedingJoinPoint proceedingJoinPoint;
    @Mock
    Throwable error;
    @Mock
    List<User> listUsers;
    @Mock
    private UserService service;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAfterThrowingAdvice() {
        userServiceAspect.afterThrowingAdvice(proceedingJoinPoint, error);
    }

    @Test
    void testBeforeAddingUser() {
        User result = userServiceAspect.beforeAddingUser(null, new User(Long.valueOf(1), "firstName", "lastName", "email"));
        Assertions.assertEquals(new User(Long.valueOf(1), "firstName", "lastName", "email"), result);
    }

    @Test
    void testValidateUser(){
        boolean result= userServiceAspect.validateUser(new User(Long.valueOf(1), "firstName", "lastName", "email"));
        Assertions.assertEquals(true, result);
    }
}