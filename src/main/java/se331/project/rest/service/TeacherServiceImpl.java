package se331.project.rest.service;

import se331.project.rest.dao.TeacherDao;
import se331.project.rest.entity.Student;
import se331.project.rest.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.project.rest.security.user.UserDao;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService{

    final TeacherDao teacherDao;
    final UserDao userDao;
    @Override
    public Integer getTeachersSize() {
        return teacherDao.getTeacherSize();
    }

    @Override
    public Page<Teacher> getTeachers(Integer pageSize, Integer page) {
        return teacherDao.getTeachers(pageSize, page);
    }

    @Override
    public Page<Teacher> getTeachers(String title, Pageable pageable) {
        return null;
    }

    @Override
    public Teacher getTeacher(Long id) {
        return teacherDao.getTeacher(id);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherDao.save(teacher);
    }

    @Override
    public Teacher updateDetail(Teacher teacher) {
        Teacher updateTeacher = teacherDao.getTeacher(teacher.getId());
        if (updateTeacher != null) {
            updateTeacher.getUser().setFirstname(teacher.getName());
            updateTeacher.getUser().setLastname(teacher.getSurname());


            teacherDao.save(updateTeacher);

            userDao.save(updateTeacher.getUser());
            return updateTeacher;
        }
        return null;
    }
}