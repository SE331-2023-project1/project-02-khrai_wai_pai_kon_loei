package se331.project.rest.dao;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se331.project.rest.entity.Announcement;

import java.util.List;

@Service
public interface AnnouncementService {

    Announcement createAnnouncement(Long teacherId, Announcement announcement);

}
