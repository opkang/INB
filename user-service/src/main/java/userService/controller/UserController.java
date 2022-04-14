package userService.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import userService.entity.User;
import userService.repository.UserRepository;
import userService.service.UserService;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@Component
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){

        return userService.saveUser(user);
    }



}
