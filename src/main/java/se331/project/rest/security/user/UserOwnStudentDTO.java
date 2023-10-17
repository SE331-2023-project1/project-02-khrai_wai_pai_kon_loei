package se331.project.rest.security.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOwnStudentDTO {
    Long id;
    String name;
    String surname;
    List<String> images;
    String department;
}
