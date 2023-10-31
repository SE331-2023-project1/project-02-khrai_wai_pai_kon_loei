package se331.project.rest.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.project.rest.entity.Student;
import se331.project.rest.service.StudentService;
import se331.project.rest.util.LabMapper;

@RestController
@RequiredArgsConstructor
public class StudentController {
    final StudentService studentService;

    @GetMapping("students")
    public ResponseEntity<?> getStudentLists(@RequestParam(value = "_limit", required = false) Integer perPage,
                                             @RequestParam(value = "_page", required = false) Integer page) {
        Page<Student> pageOutput = studentService.getStudents(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getStudentDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }

    @GetMapping("students/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") Long id) {
        Student output = studentService.getStudent(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getStudentDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @PutMapping("updateStudent")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> updateStudentDetails(@RequestBody Student student) {
        return ResponseEntity.ok(LabMapper.INSTANCE.getStudentDTO(studentService.updateDetail(student)));
    }
}