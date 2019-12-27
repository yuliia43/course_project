package repair_system.encryption;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repair_system.dtos.UserRegistrationDto;
import repair_system.models.User;

import java.security.SecureRandom;

/**
 * @author Yuliia Shcherbakova ON 14.12.2019
 * @project repair_system
 */
public class UserEncryptor extends Encryptor<User> {

    public static final UserEncryptor userEncryptor = new UserEncryptor();

    private UserEncryptor(){
        super(new BCryptPasswordEncoder());
    }

    @Override
    public void encrypt(User user) {
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
    }

    @Override
    public boolean checkIfDecryptedEqualEncrypted(User user, String decrypted) {
        decrypted = encoder.encode(decrypted);
        return (decrypted.equals(user.getPassword()));
    }

    public static UserEncryptor getUserEncryptor() {
        return userEncryptor;
    }
}
