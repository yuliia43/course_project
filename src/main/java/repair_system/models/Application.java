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
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationId;
    private Integer userId;
    private String repairDetails;
    private String status;
    private Integer price;
    private String managerDetails;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private User manager;
    @OneToOne()
    @JoinColumn(name="application_id")
    private Feedback feedback;


}
