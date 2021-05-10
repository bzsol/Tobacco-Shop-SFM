package hu.sfm.utils;

import hu.sfm.entity.Product;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public interface ProductDAO extends AutoCloseable {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hu.sfm.pu");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    void saveProduct( Product p);

    void deleteProduct(Product p);

    void updateProduct(Product p);

    List<Product> getProducts();
}
