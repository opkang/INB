package userService.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import userService.entity.User;
import userService.repository.UserRepository;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userREPO;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("Inside saveUser of UserService");
        return userREPO.save(user);
    }
}
