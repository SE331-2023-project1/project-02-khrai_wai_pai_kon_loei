package se331.project.rest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String surname;
    @ElementCollection
    List<String> images;
    String department;
}
