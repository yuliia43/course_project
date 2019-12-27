package repair_system.utils;

import repair_system.encryption.Encryptor;
import repair_system.encryption.UserEncryptor;

/**
 * @author Yuliia Shcherbakova ON 27.12.2019
 * @project spring_app
 */

public class SecurityEncryptor {
    private static Encryptor encryptor = UserEncryptor.getUserEncryptor();

    public static Encryptor getEncryptor() {
        return encryptor;
    }
}
