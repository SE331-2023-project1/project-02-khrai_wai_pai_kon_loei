package se331.project.rest.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findByIdContainingOrFirstnameContainingOrLastnameContaining(Integer keyword, String keyword1, String keyword2) {
        return userRepository.findByIdContainingOrFirstnameContainingOrLastnameContaining(keyword,keyword1,keyword2);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}