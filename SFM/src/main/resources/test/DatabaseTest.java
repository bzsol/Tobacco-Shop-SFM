import hu.sfm.entity.Permission;
import hu.sfm.entity.User;
import hu.sfm.utils.JPAUserDAO;
import hu.sfm.utils.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    @Test
    void isRegistered() {
        boolean contains = false;
        // Create Test user
        User testUser = new User();
        testUser.setUsername("TesztElek");
        testUser.setPassword("Kenyer911420");
        testUser.setPerm(Permission.ALKALMAZOTT);
        // Save the user -> JPA
        UserDAO userDAO = new JPAUserDAO();
        userDAO.saveUser(testUser);
        // Take back the user from database
        var users = userDAO.getUser();
        for(var user : users){
            if (testUser.equals(user)) {
                contains = true;
                break;
            }
        }
        assertTrue(contains);
    }
    @Test
    void isDeleted(){
        // Create Test User
        User testUser = new User();
        testUser.setUsername("TesztElek");
        testUser.setPassword("Kenyer911420");
        testUser.setPerm(Permission.ALKALMAZOTT);
        // Start the database
        UserDAO userDAO = new JPAUserDAO();
        // Get the users
        var users = userDAO.getUser();

        // If the test user is in the database or not
        if (!users.contains(testUser)) {
            userDAO.saveUser(testUser);
        }
        userDAO.deleteUser(testUser);

        var updated_users = userDAO.getUser();

        assertFalse(updated_users.contains(testUser));
    }

    @Test
    void NullTestCreate(){
        User testUser = new User();
        UserDAO userDAO = new JPAUserDAO();
        userDAO.saveUser(testUser);
        var users = userDAO.getUser();
        for(var user : users){
            if(user.getUsername() == null || user.getPassword() == null || user.getPerm() == null){
                fail();
            }
        }
    }

}
