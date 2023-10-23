package se331.project.rest.security.user;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User save(User user);

    @Transactional
    User findByUsername(String username);

    List<User> searchUser(String keyword, Integer number);
}