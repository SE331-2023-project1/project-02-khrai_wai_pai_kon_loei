package se331.project.rest.config;

import jakarta.transaction.Transactional;
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
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        userRepository.save(User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .roles(List.of(Role.ROLE_ADMIN))
                .build());

        User userT1 = new User();
        userT1.setUsername("MM");
        userT1.setFirstname("Mr. Mock");
        userT1.setLastname("Kingbird");
        userT1.setPassword("password");
        userT1.setRoles(List.of(Role.ROLE_TEACHER));
        userRepository.save(userT1);

        User userS1 = new User();
        userS1.setUsername("Thiwakon");
        userS1.setFirstname("Solid State");
        userS1.setLastname("Of America");
        userS1.setPassword("passwasdord");
        userS1.setRoles(List.of(Role.ROLE_STUDENT));
        userRepository.save(userS1);



        Teacher t1 = new Teacher();
        t1.setUser(userT1);
        teacherRepository.save(t1);



        Student s1 = new Student();
        s1.setUser(userS1);
        s1.setTeacher(t1);
        studentRepository.save(s1);


    }
}

//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        User user1 = userRepository.save(User.builder()
//                .username("admin")
//                .password(encoder.encode("admin"))
//                .firstname("admin")
//                .lastname("admin")
//                .email("admin@admin.com")
//                .roles(List.of(Role.ROLE_ADMIN))
//                .build());
//
//        Teacher teacher1 = teacherRepository.save(Teacher.builder()
//                .build());
//        Student student1 = studentRepository.save(Student.builder()
//                .build());
//
//        // Establish relationships
//        user1.setTeacher(teacher1);
//        teacher1.getStudents().add(student1);
//        student1.setTeacher(teacher1);
//
//        // Save the entities with the relationships
//        userRepository.save(user1);
//        teacherRepository.save(teacher1);
//        studentRepository.save(student1);
//    }
//}

//        Student s1 = studentRepository.save(Student.builder()
//                .name("Pattanachai")
//                .surname("Nuyamang").build());
//        studentRepository.save(Student.builder()
//                .name("Danaikrit")
//                .surname("Jaiwong").build());
//        studentRepository.save(Student.builder()
//                .name("Pharunya")
//                .surname("Liwiriyasakun").build());
//
//       Teacher t1 = teacherRepository.save(Teacher.builder()
//                .name("CHARTCHAI")
//                .surname("DOUNGSA-ARD").build());
//        teacherRepository.save(Teacher.builder()
//                .name("PASSAKORN")
//                .surname("PHANNACHITTA").build());
//        teacherRepository.save(Teacher.builder()
//                .name("PATHATHAI")
//                .surname("NALUMPOON").build());
//
//t1.getStudents().add(s1);
//s1.setTeacher(t1);
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
//
//}
