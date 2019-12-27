package repair_system.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repair_system.dtos.SecureUser;
import repair_system.encryption.UserEncryptor;
import repair_system.factories.RolesFactory;
import repair_system.models.User;
import repair_system.services.repositoryServices.UsersService;

import java.sql.SQLException;
import java.util.Optional;

/**
 * @author Yuliia Shcherbakova ON 06.12.2019
 * @project repair_system
 */
@Service
public class UserFacade {
    @Autowired
    private UsersService usersService;


    private SecureUser getSecureUserDto(User user) {
        if(user == null)
            return null;
        SecureUser secureUser = new SecureUser();
        secureUser.setUserId(user.getUserId());
        secureUser.setFirstName(user.getFirstName());
        secureUser.setLastName(user.getLastName());
        secureUser.setRole(RolesFactory.getRole(user.getRole()));
        secureUser.setEmail(user.getEmail());
        return secureUser;
    }

    public SecureUser checkAuthorizationInfo(String email, String password) throws SQLException {
        Optional<User> user = usersService.getUserByEmail(email);
        if(!user.isPresent()) return null;
        else {
            boolean authorised = UserEncryptor
                    .getUserEncryptor().checkIfDecryptedEqualEncrypted(user.get(), password);
            if (!authorised)
                return null;
            return getSecureUserDto(user.get());
        }
    }

    public SecureUser getOneById(int id) throws SQLException {
        User user = usersService.getOneById(id);
        return getSecureUserDto(user);
    }

    public SecureUser getUserByEmail(String email) {
        Optional<User> user = usersService.getUserByEmail(email);
        return getSecureUserDto(user.get());
    }
}
