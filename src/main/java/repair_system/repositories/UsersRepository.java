package repair_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repair_system.models.User;

import java.util.Optional;

/**
 * @author Yuliia Shcherbakova ON 06.12.2019
 * @project repair_system
 */
@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    Optional<User> getUserByEmail(String email);
    User getOneByUserId(Integer id);
}
