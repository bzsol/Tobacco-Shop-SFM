package hu.sfm.utils;

public class UserPassChecker {
    public static boolean passCheck(String password){
        return ((password.chars().filter(Character::isDigit).count() > 0) && (password.chars().filter(Character::isUpperCase).count() > 0) && (password.chars().count()) < 16 && (password.chars().count() > 7) && (password.chars().filter(Character::isWhitespace).count() == 0) && (password.chars().filter(Character::isLowerCase).count() > 0));
    }
    public static boolean UsernameCheck(String username){
        return (username.chars().count() > 5 && username.chars().count() < 16 && (username.chars().filter(Character::isWhitespace).count() == 0) && (username.chars().filter(Character::isLetterOrDigit).count() == username.length()));
    }
}
