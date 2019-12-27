package repair_system.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import repair_system.models.Application;

import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 06.12.2019
 * @project repair_system
 */

public interface ApplicationsRepository extends JpaRepository<Application, Integer> {
    Application getOneByApplicationId(Integer id);
    List<Application> getAllByStatusIsIn(List<String> statuses);
    List<Application> getAllByStatus(String status);
    @Query(value = "SELECT applications.* FROM applications " +
            "join feedbacks on applications.application_id = feedbacks.application_id " +
            "join users on users.user_id = feedbacks.master_id " +
            "where users.email = :email and applications.status = 'in_process';",
            nativeQuery = true)
    List<Application> getAllUnfinishedForMasterEmail(@Param("email") String email);
    List<Application> getAllByUserId(Integer userId);
}
