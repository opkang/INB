package userService.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import userService.entity.User;
import userService.service.UserService;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public User saveUser(User user){
        log.info("Inside saveUser of UserController");
        return userService.saveUser(user);
    }
}
