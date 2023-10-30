package se331.project.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.project.rest.entity.*;
import se331.project.rest.security.user.User;
import se331.project.rest.security.user.UserDTO;

import java.util.List;
@Mapper
public interface LabMapper {
        LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

        StudentDTO getStudentDTO(Student student);

        List<StudentDTO> getStudentDTO(List<Student> students);

        TeacherDTO getTeacherDTO(Teacher teacher);

        List<TeacherDTO> getTeacherDTO(List<Teacher> teachers);

        UserDTO getUserDTO(User user);

        List<UserDTO> getUserDTO(List<User> users);
        @Mapping(source = "teacher.user.firstname", target = "firstname")
        @Mapping(source = "teacher.user.lastname", target = "lastname")
        @Mapping(source = "teacher.user.username", target = "username")
//        @Mapping(source = "teacher.user.image", target = "image")
        AnnouncementDTO getAnnouncementDTO(Announcement announcement);

        AnnouncementDTO getAnnouncementDTO(User user);

        List<AnnouncementDTO> getAnnouncementDTO(List<Announcement> announcements);
    }
