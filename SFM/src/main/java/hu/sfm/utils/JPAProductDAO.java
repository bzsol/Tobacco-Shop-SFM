package hu.sfm.utils;

import hu.sfm.entity.Product;


import javax.persistence.TypedQuery;
import java.util.List;

public class JPAProductDAO implements ProductDAO{
    @Override
    public void saveProduct(Product p) {
        entityManager.getTransaction().begin();
        entityManager.persist(p);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteProduct(Product p) {
        entityManager.getTransaction().begin();
        entityManager.remove(p);
        entityManager.getTransaction().commit();

    }

    @Override
    public void updateProduct(Product p) {
        saveProduct(p);

    }

    @Override
    public List<Product> getProducts() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> product = query.getResultList();
        return product;
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();

    }
}
