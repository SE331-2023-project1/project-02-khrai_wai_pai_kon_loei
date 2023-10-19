package se331.project.rest.entity;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentOwnTeacherDTO{
    Long id;
    @ElementCollection
    List<String> images;
    String department;
}
