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
        User userT1 = new User();
        userT1.setUsername("MM");
        userT1.setFirstname("Mr. Mock");
        userT1.setLastname("Kingbird");
        userT1.setPassword("password");
        userT1.setRoles(List.of(Role.ROLE_ADMIN));
        userRepository.save(userT1);


        User userT2 = new User();
        userT2.setUsername("SS");
        userT2.setFirstname("Solid State");
        userT2.setLastname("Of America");
        userT2.setPassword("passasdword");
        userT2.setRoles(List.of(Role.ROLE_ADVISOR));
        userRepository.save(userT2);


        Teacher t1 = new Teacher();
        t1.setUser(userT1);
        teacherRepository.save(t1);

        User userS1 = new User();
        userS1.setUsername("Thiwakon");
        userS1.setFirstname("Solid State");
        userS1.setLastname("Of America");
        userS1.setPassword("passwasdord");
        userS1.setRoles(List.of(Role.ROLE_ADMIN));
        userRepository.save(userS1);

        User userS2 = new User();
        userS2.setUsername("Pat");
        userS2.setFirstname("Pattana");
        userS2.setLastname("Pattana");
        userS2.setPassword("passwasdord");
        userS2.setRoles(List.of(Role.ROLE_ADMIN));
        userRepository.save(userS2);

        Student s1 = new Student();
        s1.setUser(userS1);
        s1.setTeacher(t1);
        studentRepository.save(s1);

        Teacher t2 = new Teacher();


        Student s2 = new Student();

        s2.setUser(userS2);
        s2.setTeacher(t2);
        studentRepository.save(s2);

        t2.setUser(userT2);
        t2.getStudents().add(s2);
        s2.setTeacher(t2);
        teacherRepository.save(t2);
        studentRepository.save(s2);

    }
}
//        studentRepository.save(Student.builder()
//                .name("Pattanachai")
//                .surname("Nuyamang").build());
//        studentRepository.save(Student.builder()
//                .name("Danaikrit")
//                .surname("Jaiwong").build());
//        studentRepository.save(Student.builder()
//                .name("Pharunya")
//                .surname("Liwiriyasakun").build());
//
//      Teacher t1 =  teacherRepository.save(Teacher.builder()
//                .name("CHARTCHAI")
//                .surname("DOUNGSA-ARD").build());
//        teacherRepository.save(Teacher.builder()
//                .name("PASSAKORN")
//                .surname("PHANNACHITTA").build());
//        teacherRepository.save(Teacher.builder()
//                .name("PATHATHAI")
//                .surname("NALUMPOON").build());
//        addUser();
//user1.getTeacher().add(t1);
//t1.setUsers(user1);
//    }
//    User user1, user2, user3;
//
//    private void addUser() {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        user1 = User.builder()
//                .username("admin")
//                .password(encoder.encode("admin"))
//                .firstname("admin")
//                .lastname("admin")
//                .email("admin@admin.com")
//                .roles(List.of(Role.ROLE_ADMIN, Role.ROLE_ADVISEE))
//                .build();
//
//        user2 = User.builder()
//                .username("user")
//                .password(encoder.encode("user"))
//                .firstname("user")
//                .lastname("user")
//                .email("enabled@user.com")
//                .roles(List.of(Role.ROLE_ADVISEE))
//                .build();
//
//        user3 = User.builder()
//                .username("disableUser")
//                .password(encoder.encode("disableUser"))
//                .firstname("disableUser")
//                .lastname("disableUser")
//                .email("disableUser@user.com")
//                .roles(List.of(Role.ROLE_FASTFIT))
//                .build();
//
////        authorityRepository.save(authUser);
////        authorityRepository.save(authAdmin);
////
////        user1.getAuthorities().add(authUser);
////        user1.getAuthorities().add(authAdmin);
////        user2.getAuthorities().add(authUser);
////        user3.getAuthorities().add(authUser);
//
//        userRepository.save(user1);
//        userRepository.save(user2);
//        userRepository.save(user3);
//    }

//
//}
