package se331.project.rest.security.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserDao userDao;

    @Override
    @Transactional
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> searchUser(String keyword, Integer number) {
            // Implement a custom query using Spring Data JPA to search for courses
        return userDao.findByIdContainingOrFirstnameContainingOrLastnameContaining(number, keyword, keyword);


    }
}