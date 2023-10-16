package se331.project.rest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.project.rest.entity.Student;
import se331.project.rest.entity.Teacher;
import se331.project.rest.repository.StudentRepository;
import se331.project.rest.repository.TeacherRepository;
import se331.project.rest.security.user.Role;
import se331.project.rest.security.user.User;
import se331.project.rest.security.user.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final StudentRepository studentRepository;
    final TeacherRepository teacherRepository;
    final UserRepository userRepository;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        studentRepository.save(Student.builder()
                .name("Pattanachai")
                .surname("Nuyamang").build());
        studentRepository.save(Student.builder()
                .name("Danaikrit")
                .surname("Jaiwong").build());
        studentRepository.save(Student.builder()
                .name("Pharunya")
                .surname("Liwiriyasakun").build());

        teacherRepository.save(Teacher.builder()
                .name("CHARTCHAI")
                .surname("DOUNGSA-ARD").build());
        teacherRepository.save(Teacher.builder()
                .name("PASSAKORN")
                .surname("PHANNACHITTA").build());
        teacherRepository.save(Teacher.builder()
                .name("PATHATHAI")
                .surname("NALUMPOON").build());
        addUser();
    }
    User user1, user2, user3;

    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .roles(List.of(Role.ROLE_ADMIN, Role.ROLE_ADVISEE))
                .build();

        user2 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .email("enabled@user.com")
                .roles(List.of(Role.ROLE_ADVISEE))
                .build();

        user3 = User.builder()
                .username("disableUser")
                .password(encoder.encode("disableUser"))
                .firstname("disableUser")
                .lastname("disableUser")
                .email("disableUser@user.com")
                .roles(List.of(Role.ROLE_FASTFIT))
                .build();

//        authorityRepository.save(authUser);
//        authorityRepository.save(authAdmin);
//
//        user1.getAuthorities().add(authUser);
//        user1.getAuthorities().add(authAdmin);
//        user2.getAuthorities().add(authUser);
//        user3.getAuthorities().add(authUser);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }


}
