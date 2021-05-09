import hu.sfm.entity.Product;
import hu.sfm.utils.JPAProductDAO;
import hu.sfm.utils.ProductDAO;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JPAProductTest {
    @Test
    void IsProductsInDatabase(){

        ProductDAO productDAO = new JPAProductDAO();
        var products = productDAO.getProducts();
        assertEquals(products.size(),61);
    }
    @Test
    void IsProductDatabaseGetMax(){
        ProductDAO productDAO = new JPAProductDAO();
        var products = productDAO.getProducts();
        var maxquantity = products.stream().max(Comparator.comparing(Product::getQuantity)).orElseThrow(NoSuchElementException::new);
        assertEquals(maxquantity.getQuantity(),250);
    }
}
