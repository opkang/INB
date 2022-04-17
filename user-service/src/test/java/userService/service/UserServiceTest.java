package userService.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import userService.entity.User;
import userService.service.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    Logger log;
    @InjectMocks
    UserService userService=new UserService();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveUser() {
        User result = userService.saveUser(new User(Long.valueOf(1), "firstName", "lastName", "email"));
        Assertions.assertEquals(new User(Long.valueOf(1), "firstName", "lastName", "email"), result);
    }

    @Test
    void testListAll() {

        List<User> result = userService.listAll();
        Assertions.assertEquals(Arrays.<User>asList(new User(Long.valueOf(1), "firstName", "lastName", "email")), result);
    }

}
