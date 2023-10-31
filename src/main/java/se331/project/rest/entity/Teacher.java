package se331.project.rest.entity;
import jakarta.persistence.*;
import lombok.*;
import se331.project.rest.security.user.Role;
import se331.project.rest.security.user.User;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String surname;
    @ElementCollection
    List<String> images;
    String department;
    @OneToOne
    User user;

    @OneToMany(mappedBy = "teacher")
    @Builder.Default
    private List<Student> students = new ArrayList<>();
}
