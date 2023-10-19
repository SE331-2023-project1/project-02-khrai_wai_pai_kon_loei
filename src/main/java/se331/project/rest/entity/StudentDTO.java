package se331.project.rest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    Long id;

    List<String> images;
    String department;
    StudentOwnTeacherDTO teacher;
    StudentOwnUserDTO user;
}
