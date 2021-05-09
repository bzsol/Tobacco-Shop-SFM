package hu.sfm.utils;

import hu.sfm.entity.Product;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public interface ProductDAO extends AutoCloseable {
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void saveProduct( Product p);

    public void deleteProduct(Product p);

    public void updateProduct(Product p);

    public List<Product> getProducts();
}
