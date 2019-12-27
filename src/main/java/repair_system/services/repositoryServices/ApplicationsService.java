package repair_system.services.repositoryServices;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repair_system.enums.Status;
import repair_system.factories.StatusFactory;
import repair_system.models.Application;
import repair_system.models.Feedback;
import repair_system.models.User;
import repair_system.repositories.ApplicationsRepository;
import repair_system.services.TransactionService;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 08.12.2019
 * @project repair_system
 */
@Service
public class ApplicationsService {

    @Autowired
    private ApplicationsRepository repository;
    @Autowired
    private FeedbacksService feedbackService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private TransactionService transactionService;

    private static Logger logger = Logger.getLogger(ApplicationsService.class);


    public List<Application> getAllByUserId(int userId) {
        List<Application> applications = repository.getAllByUserId(userId);
        applications.stream()
                .forEach((application -> {
                    if (application.getStatus().equals(StatusFactory.getStringValue(Status.IN_PROCESS))
                            || application.getStatus().equals(StatusFactory.getStringValue(Status.FINISHED))) {
                        setFeedback(application);
                    }
                    if(!application.getStatus().equals(StatusFactory.getStringValue(Status.CREATED)))
                    setManager(application);
                }));
        return applications;
    }

    public void setManager(Application application) {
        User manager = usersService.getOneById(application.getManager().getUserId());
        application.setManager(manager);
    }

    public void setFeedback(Application application) {
        try {
            Feedback feedback = feedbackService
                    .getAllByApplicationId(application.getApplicationId());
            application.setFeedback(feedback);
        } catch (SQLException e) {
            logger.error(ErrorMessage.SQL_EXCEPTION);
        }
    }

    public List<Application> getAllNotViewed() {
        return repository.getAllByStatus(StatusFactory.getStringValue(Status.CREATED));
    }

    public List<Application> getAllAccepted() {
        return repository.getAllByStatus(StatusFactory.getStringValue(Status.ACCEPTED));
    }

    @Transactional
    public void updateAll(String[] applicationIds) {
        List<Application> applications = new ArrayList<>();
        for (String applicationId : applicationIds) {
            Application application = repository.getOneByApplicationId(Integer.valueOf(applicationId));
            application.setStatus(StatusFactory.getStringValue(Status.FINISHED));
            applications.add(application);
        }
        transactionService.updateAllTransaction(applications);
    }

    public List<Application> getAllMastersStats() {
        List<String> mastersStatusesList = new ArrayList<>();
        mastersStatusesList.add(StatusFactory.getStringValue(Status.IN_PROCESS));
        mastersStatusesList.add(StatusFactory.getStringValue(Status.FINISHED));
        List<Application> stats = repository
                .getAllByStatusIsIn(mastersStatusesList);
        stats.stream()
                .forEach((application -> {
                    setFeedback(application);
                }));
        return stats;
    }

    public List<Application> getAllUnfinishedForMasterEmail(String email) {
        List<Application> applications = repository.getAllUnfinishedForMasterEmail(email);
        applications.stream()
                .forEach((application -> {
                    setFeedback(application);
                    setManager(application);
                }));
        return applications;
    }

    public void add(Application application) {
        repository.saveAndFlush(application);
    }

    public void update(Application application) {
        repository.saveAndFlush(application);
    }

    public Application getOneById(int applicationId) {
        return repository.getOne(applicationId);
    }
}
