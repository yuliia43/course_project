package repair_system.dtos;

import lombok.*;

/**
 * @author Yuliia Shcherbakova ON 26.12.2019
 * @project spring_app
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackDto {
    private Integer feedbackId;
    private String feedback;
}
