package repair_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repair_system.enums.Status;
import repair_system.factories.StatusFactory;
import repair_system.models.Application;
import repair_system.models.Feedback;
import repair_system.repositories.ApplicationsRepository;
import repair_system.repositories.FeedbacksRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 10.12.2019
 * @project repair_system
 */
@Service
@Transactional
public class TransactionService {

    @Autowired
    private FeedbacksRepository feedbacksRepository;
    @Autowired
    private ApplicationsRepository applicationsRepository;

    public void takeApplication(List<Feedback> feedbacks) {
        for (Feedback feedback :
                feedbacks) {
            feedbacksRepository.save(feedback);
            Application application = applicationsRepository
                    .getOneByApplicationId(feedback.getApplicationId());
            application.setStatus(StatusFactory.getStringValue(Status.IN_PROCESS));
            applicationsRepository.save(application);
            /*if (!commitChanges(connection)) return;*/
        }
        feedbacksRepository.flush();
        applicationsRepository.flush();
    }

    public void updateAllTransaction(List<Application> applications) {
        for (Application application :
                applications) {
                applicationsRepository.save(application);
        }
    }
}
