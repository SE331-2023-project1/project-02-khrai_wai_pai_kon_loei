package se331.project.rest.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import se331.project.rest.entity.Announcement;

import java.util.List;
public interface AnnouncementRepository extends JpaRepository<Announcement,Long>{

    List<Announcement> findAll();

//    Announcement findById(Long Id);
}
