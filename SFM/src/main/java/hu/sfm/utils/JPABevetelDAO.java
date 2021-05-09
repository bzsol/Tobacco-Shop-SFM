package hu.sfm.utils;

import hu.sfm.entity.Bevetel;


import javax.persistence.TypedQuery;
import java.util.List;

public class JPABevetelDAO implements BevetelDAO{
    @Override
    public void saveBevetel(Bevetel b) {
        entityManager.getTransaction().begin();
        entityManager.persist(b);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteBevetel(Bevetel b) {
        entityManager.getTransaction().begin();
        entityManager.remove(b);
        entityManager.getTransaction().commit();

    }

    @Override
    public void updateBevetel(Bevetel b) {
        saveBevetel(b);

    }


    @Override
    public List<Bevetel> getBevetelek() {
        TypedQuery<Bevetel> query = entityManager.createQuery("SELECT p FROM Bevetel p", Bevetel.class);
        List<Bevetel> bevetel = query.getResultList();
        return bevetel;
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();

    }
}
