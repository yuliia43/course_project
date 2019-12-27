package repair_system.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yuliia Shcherbakova ON 06.12.2019
 * @project repair_system
 */
public class MatchingValidator {
    private static Matcher matcher;
    private static Pattern pattern;
    private static Map<String, Pattern> patternMap = new HashMap<>();

    private MatchingValidator() {
    }

    static {
        patternMap.put("namePattern", Pattern.compile("[a-zA-ZА-Яа-я]+[/-/']?[a-zA-ZА-Яа-я]*"));
        patternMap.put("emailPattern", Pattern.compile("[a-zA-ZА-Яа-я0-9_]+[@]{1}[a-zA-ZА-Яа-я]+[/.]{1}[a-zA-ZА-Яа-я]+"));
    }

    public static boolean nameMatches(String name) {
        pattern = patternMap.get("namePattern");
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean emailMatches(String email) {
        pattern = patternMap.get("emailPattern");
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
