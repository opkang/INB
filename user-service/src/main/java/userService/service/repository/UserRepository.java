package userService.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import userService.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
