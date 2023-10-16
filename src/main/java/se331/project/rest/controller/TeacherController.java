package se331.project.rest.controller;
import  se331.project.rest.entity.Teacher;
import se331.project.rest.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import se331.project.rest.util.LabMapper;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    final TeacherService teacherService;

    @GetMapping("teachers")
    public ResponseEntity<?> getTeacherLists(@RequestParam(value = "_limit", required = false) Integer perPage,
                                             @RequestParam(value = "_page", required = false) Integer page) {
        Page<Teacher> pageOutput = teacherService.getTeachers(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getTeacherDTO(pageOutput.getContent()),responseHeader, HttpStatus.OK);
    }
    @GetMapping("teachers/{id}")
    public ResponseEntity<?> getTeacher(@PathVariable("id") Long id) {
        Teacher output = teacherService.getTeacher(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getTeacherDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}