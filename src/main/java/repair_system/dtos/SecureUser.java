package repair_system.dtos;

import lombok.*;
import repair_system.enums.Role;
import repair_system.factories.RolesFactory;

/**
 * @author Yuliia Shcherbakova ON 06.12.2019
 * @project spring_app
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecureUser {
    private int userId;
    private String firstName;
    private String lastName;
    private Role role;
    private String email;

    public String getRole() {
        return RolesFactory.getStringValue(role);
    }
}
