import hu.sfm.entity.Permission;
import hu.sfm.entity.Product;
import hu.sfm.entity.ProductGroups;
import hu.sfm.entity.User;
import hu.sfm.utils.JPAProductDAO;
import hu.sfm.utils.JPAUserDAO;
import hu.sfm.utils.ProductDAO;
import hu.sfm.utils.UserDAO;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    @Test
    void IsProductDatabase(){
        // Create test product
        Product testProduct = new Product();
        testProduct.setPrice(420);
        testProduct.setName("Nagyapa féle gyilkos cucc");
        testProduct.setQuantity(42);
        testProduct.setProductGroups(ProductGroups.SZESZESITAL);

        // Database connection
        ProductDAO productDAO = new JPAProductDAO();
        productDAO.saveProduct(testProduct);

        // Get the Products
        var products = productDAO.getProducts();
        assertTrue( products.contains(testProduct));
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



}
