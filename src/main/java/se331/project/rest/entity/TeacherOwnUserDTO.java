package se331.project.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherOwnUserDTO {
    private Integer id;
    private String parentId;
    private String firstname;
    private String lastname;
}
