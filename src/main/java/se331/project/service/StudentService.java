package se331.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.project.entity.Student;

public interface StudentService {
    Integer getStudentsSize();

    Page<Student> getStudents(Integer pageSize, Integer page);

    Page<Student> getStudents(String title, Pageable pageable);

    Student getStudent(Long id);

    Student save(Student student);

}
