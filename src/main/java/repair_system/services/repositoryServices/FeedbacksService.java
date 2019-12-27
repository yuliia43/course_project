package repair_system.services.repositoryServices;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repair_system.models.Feedback;
import repair_system.models.User;
import repair_system.repositories.FeedbacksRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 09.12.2019
 * @project repair_system
 */
@Service
public class FeedbacksService {

    @Autowired
    private FeedbacksRepository repository;
    @Autowired
    private UsersService usersService;

    public List<Feedback> getAll() throws SQLException {
        List<Feedback> feedbacks = repository.findAll();
        addMasterObjectsToFeedbacks(feedbacks);
        return feedbacks;
    }

    public Feedback getAllByApplicationId(int applicationId) {
        Feedback feedbacks = repository.getOneByApplicationId(applicationId);
        setMaster(feedbacks);
        return feedbacks;
    }

    private void addMasterObjectsToFeedbacks(List<Feedback> feedbacks) {
        feedbacks.stream()
                .forEach(feedback -> {
                    setMaster(feedback);
                });
    }


    private void setMaster(Feedback feedback) {
        User user = usersService.getOneById(feedback.getMaster().getUserId());
        feedback.setMaster(user);
    }


    public void setFeedback(Feedback feedback) {
        repository.saveAndFlush(feedback);
    }

    public Feedback getFeedbackById(Integer feedbackId) {
        return repository.getOne(feedbackId);
    }
}
