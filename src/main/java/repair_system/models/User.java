package repair_system.models;

import lombok.*;

import javax.persistence.*;

/**
 * @author Yuliia Shcherbakova ON 06.12.2019
 * @project repair_system
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String password;
}
