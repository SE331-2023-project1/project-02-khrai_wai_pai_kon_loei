package se331.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.project.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAll();
}
