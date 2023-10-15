package se331.project.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.project.rest.entity.Teacher;
import java.util.List;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findAll();
}