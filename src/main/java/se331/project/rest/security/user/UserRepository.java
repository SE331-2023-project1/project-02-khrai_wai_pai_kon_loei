package se331.project.rest.security.user;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findByEmail(String email);

  Optional<User> findByUsername(String username);
  List<User> findAll();

  List<User> findByIdContainingOrFirstnameContainingOrLastnameContaining(Integer id, String firstname, String lastname);
}
