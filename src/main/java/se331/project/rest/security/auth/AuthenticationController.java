package se331.project.rest.security.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(service.advisorRegister(request));
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

    @GetMapping("/advisor")
    public ResponseEntity<?> getAdvisor(

    ) {
        return ResponseEntity.ok("hi veevi");
    }


    @GetMapping("/all-Advisor")
    public ResponseEntity<?> getAllAdvisor(

    ) {
        return ResponseEntity.ok("hi veevi eiei");
    }
}
