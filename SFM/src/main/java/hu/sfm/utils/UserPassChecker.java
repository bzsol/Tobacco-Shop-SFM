package hu.sfm.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserPassChecker {
    private static final String userRegex = "^[A-z0-9]{5,}$";
    private static final String passRegex = "^(?=.*[A-Z])(?=.*[0-9])[A-z0-9]{7,}$";

    public static boolean passCheck(String password){
        Pattern pattern = Pattern.compile(passRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public static boolean UsernameCheck(String username){
        Pattern pattern = Pattern.compile(userRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
}