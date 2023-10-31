package se331.project.rest.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.project.rest.dao.StudentDao;
import se331.project.rest.entity.Student;
import se331.project.rest.security.user.UserDao;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    final StudentDao studentDao;
    final UserDao userDao;
    @Override
    public Integer getStudentsSize() {
        return studentDao.getStudentSize();
    }

    @Override
    public Page<Student> getStudents(Integer pageSize, Integer page) {
        return studentDao.getStudents(pageSize, page);
    }

    @Override
    public Page<Student> getStudents(String title, Pageable pageable) {
        return null;
    }

    @Override
    public Student getStudent(Long id) {
        return studentDao.getStudent(id);
    }

    @Override
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student updateDetail(Student student) {
        Student updateStudent = studentDao.getStudent(student.getId());
        if (updateStudent != null) {
            updateStudent.getUser().setFirstname(student.getName());
            updateStudent.getUser().setLastname(student.getSurname());


            studentDao.save(updateStudent);

            userDao.save(updateStudent.getUser());
            return updateStudent;
        }
        return null;
    }
}
