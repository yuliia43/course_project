package repair_system.encryption;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Yuliia Shcherbakova ON 14.12.2019
 * @project repair_system
 */
public abstract class Encryptor<T> {
    protected PasswordEncoder encoder;

    public Encryptor(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    abstract void encrypt(T item);
    abstract boolean checkIfDecryptedEqualEncrypted(T item, String decrypted);
}
