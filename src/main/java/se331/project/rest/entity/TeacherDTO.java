package se331.project.rest.entity;
import jakarta.persistence.*;
import lombok.*;
import se331.project.rest.security.user.User;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    Long id;

    @ElementCollection
    List<String> images;
    String department;
    @Builder.Default
    List<TeacherOwnStudentDTO> students = new ArrayList<>();
    TeacherOwnUserDTO user;
}
