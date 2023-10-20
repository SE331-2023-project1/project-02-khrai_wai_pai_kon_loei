package se331.project.rest.security.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.project.rest.entity.Student;
import se331.project.rest.util.LabMapper;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    //student login
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("advisor/register")
    public ResponseEntity<AuthenticationResponse> advisorRegister(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.teacherRegister(request));
    }

    //login
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse result = service.authenticate(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

//    @GetMapping("/advisor")
//    public ResponseEntity<?> getAdvisor(
//
//    ) {
//        return ResponseEntity.ok("hi veevi");
//    }
    @PostMapping("/register/teacher")
    public ResponseEntity<?> teacherRegister(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.teacherRegister(request));
    }
    @PutMapping("/update/teacher")
    public ResponseEntity<?> teacherUpdate(@RequestBody RegisterRequest request){
    return ResponseEntity.ok(service.UpdateInfo(request));
    }
    @PutMapping("/update/student")
    public ResponseEntity<?> studentUpdate(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.UpdateInfo(request));
    }
//    @GetMapping("/all-Advisor")
//    public ResponseEntity<?> getAllAdvisor(
//
//    ) {
//        return ResponseEntity.ok("hi veevi eiei");
//    }
    @PutMapping("/setStudentToTeacher")
    public  ResponseEntity<?> setStudentToTeacher(@RequestBody RegisterRequest request){
        Student setStudentToTeacher = service.setStudentToTeacher(request);
        return ResponseEntity.ok(LabMapper.INSTANCE.getStudentDTO(setStudentToTeacher));
    }
}
