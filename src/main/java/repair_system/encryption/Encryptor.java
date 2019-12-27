package repair_system.encryption;

import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Yuliia Shcherbakova ON 14.12.2019
 * @project repair_system
 */
@Getter
public abstract class Encryptor<T> {
    protected PasswordEncoder encoder;

    public Encryptor(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public abstract void encrypt(T item);
    public abstract boolean checkIfDecryptedEqualEncrypted(T item, String decrypted);
}
