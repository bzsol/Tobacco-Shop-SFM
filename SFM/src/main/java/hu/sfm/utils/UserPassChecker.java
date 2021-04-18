package hu.sfm.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserPassChecker {
    private static final String userRegex = "^[A-z0-9]{5,}$";
    private static final String passRegex = "^(?=.*[A-Z])(?=.*[0-9])[A-z0-9]{7,}$";
    private static final String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    private static final String nameRegex = "^[A-Z-ÁÉÍÓÖŐÚÜŰ][a-z-áéíóöőúüű]+$";
    private static final String dobRegex = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$"; // yyyy-mm-dd

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
    public static boolean EmailChecker(String email){
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean NameCheck(String Name){
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(Name);
        return matcher.matches();
    }
    public static boolean dobCheck(String Date){
        Pattern pattern = Pattern.compile(dobRegex);
        Matcher matcher = pattern.matcher(Date);
        return matcher.matches();
    }
}