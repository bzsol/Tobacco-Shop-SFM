import hu.sfm.entity.Permission;
import hu.sfm.entity.Product;
import hu.sfm.entity.ProductGroups;
import hu.sfm.entity.User;
import hu.sfm.utils.JPAProductDAO;
import hu.sfm.utils.JPAUserDAO;
import hu.sfm.utils.ProductDAO;
import hu.sfm.utils.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.OptionalLong;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    @Test
    void IsProductsInDatabase(){

        ProductDAO productDAO = new JPAProductDAO();
        var products = productDAO.getProducts();
        assertEquals(products.size(),61);
    }
    @Test
    void IsProductQuantityIsAlways100(){
        ProductDAO productDAO = new JPAProductDAO();
        var products = productDAO.getProducts();
        var maxquantity = products.stream().max(Comparator.comparing(Product::getQuantity)).orElseThrow(NoSuchElementException::new);
        assertEquals(maxquantity.getQuantity(),100);
    }
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
        assertTrue(users.contains(testUser));
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


}
