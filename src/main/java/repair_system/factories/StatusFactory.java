package repair_system.factories;

import repair_system.enums.Status;

/**
 * @author Yuliia Shcherbakova ON 10.12.2019
 * @project repair_system
 */
public class StatusFactory {

    private StatusFactory() {
    }

    public static Status getStatus(String status) {
        return Status.valueOf(status.toUpperCase());
    }

    public static String getStringValue(Status status) {
        return status.toString().toLowerCase();
    }
}
