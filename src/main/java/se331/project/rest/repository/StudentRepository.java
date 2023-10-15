package se331.project.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.project.rest.entity.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAll();
}
