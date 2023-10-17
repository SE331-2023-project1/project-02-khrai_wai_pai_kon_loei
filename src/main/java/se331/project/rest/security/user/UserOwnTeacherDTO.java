package se331.project.rest.security.user;

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
public class UserOwnTeacherDTO {
    Long id;
    String name;
    String surname;
    @ElementCollection
    List<String> images;
    String department;
}
