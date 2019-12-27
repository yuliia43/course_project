package repair_system.dtos;

import lombok.*;

/**
 * @author Yuliia Shcherbakova ON 26.12.2019
 * @project spring_app
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerApplicationDto {
    private boolean confirm;
    private boolean reject;
    private int applicationId;
    private int price;
    private String details;
}
