package se331.project.rest.dao;


import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se331.project.rest.entity.Announcement;
import se331.project.rest.entity.Teacher;
import se331.project.rest.repository.TeacherRepository;
import se331.project.rest.util.CloudStorageHelper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private CloudStorageHelper cloudStorageHelper;
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Announcement createAnnouncement(Long teacherId, Announcement announcement) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);

        announcement.setTeacher(teacherOpt.get());

        return announcementRepository.save(announcement);
    }

}
