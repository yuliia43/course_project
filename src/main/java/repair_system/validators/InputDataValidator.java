package repair_system.validators;

/**
 * @author Yuliia Shcherbakova ON 06.12.2019
 * @project repair_system
 */
public class InputDataValidator {

    private InputDataValidator() {
    }

    public static boolean registrationDataNotEmpty
            (String firstName, String lastName, String role, String email, String password, String password2) {
        return !(firstName.isEmpty() || lastName.isEmpty() || role.isEmpty() || email.isEmpty()
                || password.isEmpty() || password2.isEmpty());
    }

    public static boolean registrationPasswordsEqual(String password, String password2) {
        return password.equals(password2);
    }

    public static boolean authorisationDataNotEmpty(String email, String password) {
        return !(email.isEmpty() || password.isEmpty());
    }

    public static boolean detailsDataNotEmpty(String details) {
        if (details == null) return false;
        return !details.isEmpty();
    }

    public static boolean somethingIsChosen(String[] applicationIds) {
        return applicationIds != null;
    }

    public static boolean priceNotEmpty(int price) {
        return price > 0;
    }
}
