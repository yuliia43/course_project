package repair_system.models;

import lombok.*;

import javax.persistence.*;

/**
 * @author Yuliia Shcherbakova ON 08.12.2019
 * @project repair_system
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;
    private String feedback;
    private Integer applicationId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="master_id")
    private User master;
}
