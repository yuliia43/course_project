package repair_system.dtos;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Yuliia Shcherbakova ON 26.12.2019
 * @project spring_app
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String password;
    private String passwordConfirmation;
}
