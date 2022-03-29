package userService.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import userService.entity.User;
import userService.repository.UserRepository;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

/*    @DeleteMapping("/")
    public User deleteUser(@RequestBody int id){
        log.info("Inside saveUser of UserService");
        User user = userRepository.getById(Long.valueOf(id));
        userRepository.delete(user);
        return user;
    }*/


}
