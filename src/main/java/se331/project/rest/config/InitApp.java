package se331.project.rest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.project.rest.entity.Student;
import se331.project.rest.entity.Teacher;
import se331.project.rest.repository.StudentRepository;
import se331.project.rest.repository.TeacherRepository;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final StudentRepository studentRepository;
    final TeacherRepository teacherRepository;
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
                .name("Dto")
                .surname("Dto").build());
        teacherRepository.save(Teacher.builder()
                .name("Kong")
                .surname("Passakorn").build());
        teacherRepository.save(Teacher.builder()
                .name("Tei")
                .surname("Pathathai").build());
    }
}
