package se331.project.rest.security.user;

public interface UserDao {
    User findByUsername(String username);

    User save(User user);
}