package se331.project.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.project.rest.entity.Student;
import  se331.project.rest.entity.Teacher;


public interface TeacherService {

    Integer getTeachersSize();

    Page<Teacher> getTeachers(Integer pageSize, Integer page);

    Page<Teacher> getTeachers(String title, Pageable pageable);

    Teacher getTeacher(Long id);

    Teacher save(Teacher teacher);

    Teacher updateDetail(Teacher teacher);
}