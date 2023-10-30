package se331.project.rest.dao;


import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se331.project.rest.entity.Announcement;
import se331.project.rest.entity.AnnouncementDTO;
import se331.project.rest.util.CloudStorageHelper;
import se331.project.rest.util.LabMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    @Autowired
    private CloudStorageHelper cloudStorageHelper;
    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private AnnouncementRepository announcementRepository;


    @PostMapping
    public ResponseEntity<AnnouncementDTO> createAnnouncement(@RequestParam("teacherId") Long teacherId, @RequestBody Announcement announcement) {
        AnnouncementDTO announcementDTO = LabMapper.INSTANCE.getAnnouncementDTO(announcementService.createAnnouncement(teacherId, announcement));
        return ResponseEntity.ok(announcementDTO);

    }
    @GetMapping("/")
    public ResponseEntity<List<AnnouncementDTO>> getAllAnnouncements() {
        List<Announcement> announcements = announcementRepository.findAll();
        List<AnnouncementDTO> announcementDTOs = announcements.stream()
                .map(announcement -> LabMapper.INSTANCE.getAnnouncementDTO(announcement))
                .collect(Collectors.toList());
        return ResponseEntity.ok(announcementDTOs);
    }

    @GetMapping("/{announcementId}")
    public ResponseEntity<AnnouncementDTO> getSingleAnnouncement(@PathVariable Long announcementId) {
        Optional<Announcement> announcement = announcementRepository.findById(announcementId);
        if (announcement.isPresent()) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getAnnouncementDTO(announcement.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
