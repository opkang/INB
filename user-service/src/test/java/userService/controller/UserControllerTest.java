package userService.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import userService.entity.User;
import userService.service.UserService;

import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    UserService userService;
    @Mock
    Logger log;
    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveUser() {
        when(userService.saveUser(any())).thenReturn(new User(Long.valueOf(1), "firstName", "lastName", "email"));

        User result = userController.saveUser(new User(Long.valueOf(1), "firstName", "lastName", "email"));
        Assertions.assertEquals(new User(Long.valueOf(1), "firstName", "lastName", "email"), result);
    }


}
