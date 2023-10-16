package se331.project.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se331.project.rest.entity.Student;
import se331.project.rest.entity.StudentDTO;
import se331.project.rest.entity.Teacher;

import java.util.List;
@Mapper
public interface LabMapper {
        LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

        StudentDTO getStudentDTO(Student student);

        List<StudentDTO> getStudentDTO(List<Student> students);

//        TeacherDTO getTeacherDTO(Teacher teacher);
//
//        List<TeacherDTO> getTeacherDTO(List<Teacher> teachers);

//        UserDTO getUserDTO(User user);
//
//        List<UserDTO> getUserDTO(List<User> users);
    }
