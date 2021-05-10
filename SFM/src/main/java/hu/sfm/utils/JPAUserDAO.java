package hu.sfm.utils;

import hu.sfm.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPAUserDAO implements UserDAO {

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hu.sfm.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();



    @Override
    public void saveUser(User u) {
        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteUser(User u) {
        entityManager.getTransaction().begin();
        entityManager.remove(u);
        entityManager.getTransaction().commit();

    }

    @Override
    public void updateUser(User u) {
        saveUser(u);

    }

    @Override
    public List<User> getUser() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();



    }
}
