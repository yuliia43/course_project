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

    /*public void updateAllTransaction(List<Application> applications) {

    }*/

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

    /*private boolean commitChanges(Connection connection) {
        try {
            connection.commit();
            logger.info("TakeApplication transaction commited");
        } catch (SQLException e) {
            logger.error(ErrorMessage.TRANSACTION_COMMIT_FAIL);
            setAutoCommit(connection);
            return false;
        }
        return true;
    }

    private boolean updateApplicationStatus(Connection connection, Application application) {
        try {
            applicationsRepository.updateStatus(application, connection);
        } catch (SQLException e) {
            logger.error(ErrorMessage.APPLICATION_UPDATING_FAIL);
            setAutoCommit(connection);
            return false;
        }
        return true;
    }

    private boolean addFeedback(Connection connection, Feedback feedback) {
        try {
            feedbacksRepository.add(feedback, connection);
        } catch (SQLException e) {
            logger.error(ErrorMessage.FEEDBACK_ADDING_FAIL);
            setAutoCommit(connection);
            return false;
        }
        return true;
    }

    private boolean unsetAutoCommit(Connection connection) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error(ErrorMessage.AUTO_COMMIT_FALSE_FAIL);
            closeConnection(connection);
            return false;
        }
        return true;
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ErrorMessage.CLOSING_CONNECTION_FAIL);
        }
    }

    private void setAutoCommit(Connection connection) {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            logger.error(ErrorMessage.ROLLBACK_FAIL);
        } finally {
            closeConnection(connection);
        }
    }*/

    public void updateAllTransaction(List<Application> applications) {
        for (Application application :
                applications) {
                applicationsRepository.save(application);
        }
    }
}
