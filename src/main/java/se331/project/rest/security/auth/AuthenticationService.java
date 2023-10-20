package se331.project.rest.security.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se331.project.rest.dao.StudentDao;
import se331.project.rest.dao.TeacherDao;
import se331.project.rest.entity.Student;
import se331.project.rest.entity.Teacher;
import se331.project.rest.repository.StudentRepository;
import se331.project.rest.repository.TeacherRepository;
import se331.project.rest.security.config.JwtService;
import se331.project.rest.security.token.Token;
import se331.project.rest.security.token.TokenRepository;
import se331.project.rest.security.token.TokenType;
import se331.project.rest.security.user.Role;
import se331.project.rest.security.user.User;
import se331.project.rest.security.user.UserRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;

    //register student
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder().username(request.getUsername()).firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).roles(List.of(Role.ROLE_STUDENT)).build();
        var savedUser = repository.save(user);
        Student student = new Student();
        student.setUser(savedUser);

//        Long teacherId = request.getId();
//        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
//        if (teacher != null) {
//            student.setTeacher(teacher);
//        }
//
//        assert teacher != null;
//        teacher.getStudents().add(student);
//
//        teacherRepository.save(teacher);

        studentRepository.save(student);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).userRole(user.getRoles()).build();
    }
    //register advisor
    public AuthenticationResponse teacherRegister(RegisterRequest request) {
        User advisor = User.builder().username(request.getUsername()).firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).roles(List.of(Role.ROLE_TEACHER)).build();
        var savedUser = repository.save(advisor);
        Teacher teacher = new Teacher();
        teacher.setUser(savedUser);
        teacherRepository.save(teacher);
        var jwtToken = jwtService.generateToken(advisor);
        var refreshToken = jwtService.generateRefreshToken(advisor);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).userRole(advisor.getRoles()).build();
    }

    //update teacher info
    public AuthenticationResponse UpdateInfo(RegisterRequest request) {
        User existingUser = repository.findByEmail(request.getEmail());
        if (existingUser == null) {
            return null;
        }
        existingUser.setUsername(request.getUsername());
        existingUser.setFirstname(request.getFirstname());
        existingUser.setLastname(request.getLastname());
        existingUser.setEmail(request.getEmail());
        existingUser.setPassword(request.getPassword());

        var savedUser = repository.save(existingUser);
        var jwtToken = jwtService.generateToken(existingUser);
        var refreshToken = jwtService.generateRefreshToken(existingUser);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).userRole(existingUser.getRoles()).build();
    }
//admin set student to teacher
    @Transactional
    public  Student setStudentToTeacher(RegisterRequest request){
        Student existingStudent = studentDao.getStudent(request.getId());
        if(existingStudent == null){
            return  null;
        }

        Teacher teacher = teacherDao.getTeacher(request.getId());
        existingStudent.setTeacher(teacher);
        return studentDao.save(existingStudent);
    }
    //login
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = repository.findByUsername(request.getUsername()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        // Get the user's roles and convert them to a comma-separated string
        List<Role> userRoles = user.getRoles();

//    revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken)
//                .user(LabMapper.INSTANCE.getOrganizerAuthDTO(user.getOrganizer()))
                .userRole(userRoles).build();
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false).build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User user = this.repository.findByUsername(userEmail).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                AuthenticationResponse authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
