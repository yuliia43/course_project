package repair_system.factories;

import repair_system.enums.Role;

/**
 * @author Yuliia Shcherbakova ON 10.12.2019
 * @project repair_system
 */
public class RolesFactory {

    private RolesFactory() {
    }

    public static Role getRole(String role) {
        return Role.valueOf(role.toUpperCase());
    }

    public static String getStringValue(Role role) {
        return role.toString().toLowerCase();
    }
}
