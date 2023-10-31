package se331.project.rest.controller;

import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import se331.project.rest.util.CloudStorageHelper;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class BucketController {
    final CloudStorageHelper cloudStorageHelper;

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestPart(value = "file")MultipartFile file)
//    public ResponseEntity<?> uploadFile(@RequestPart(value = "image") MultipartFile file)
            throws ServletException, IOException {
        return ResponseEntity.ok(this.cloudStorageHelper.getFileUrl(file,"se-lab-331-imageuplaod.appspot.com"));
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadFileComponent(@RequestPart(value = "image") MultipartFile file)
            throws IOException, ServletException {
        return ResponseEntity.ok(this.cloudStorageHelper.getImageUrl(file, "se-lab-331-imageuplaod.appspot.com"));
    }
}
