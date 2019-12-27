package repair_system.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Yuliia Shcherbakova ON 23.12.2019
 * @project spring_app
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorisationDto {
    private String email;
    private String password;
}
