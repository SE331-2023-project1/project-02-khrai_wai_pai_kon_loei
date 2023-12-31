package se331.project.rest.security.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.project.rest.entity.Student;
import se331.project.rest.security.user.User;
import se331.project.rest.security.user.UserService;
import se331.project.rest.util.LabMapper;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserService userService;

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

    @GetMapping("/searchUser")
    public ResponseEntity<?> searchUser(
            @RequestParam(value = "number", required = false) Integer number,
            @RequestParam(value = "keyword", required = false) String keyword){

        List<User> searchResults = userService.searchUser(keyword , number );

        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(LabMapper.INSTANCE.getUserDTO(searchResults), responseHeader, HttpStatus.OK);
    }
}
