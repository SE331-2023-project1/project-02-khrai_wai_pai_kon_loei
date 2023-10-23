package se331.project.rest.security.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDao {
    User findByUsername(String username);

    User save(User user);

    List<User> findByIdContainingOrFirstnameContainingOrLastnameContaining(Integer keyword, String keyword1, String keyword2);

    List<User> findAll();
}