package se331.project.rest.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.project.rest.entity.Student;

public interface StudentDao {
    Integer getStudentSize();
    Student save(Student student);
    Page<Student> getStudents(Integer pageSize, Integer page);
    Page<Student> getStudents(String name, Pageable page);
    Student getStudent(Long id);
}
