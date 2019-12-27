package repair_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import repair_system.models.Feedback;

import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 08.12.2019
 * @project repair_system
 */
public interface FeedbacksRepository extends JpaRepository<Feedback, Integer> {
    Feedback getOneByApplicationId(Integer id);
}
