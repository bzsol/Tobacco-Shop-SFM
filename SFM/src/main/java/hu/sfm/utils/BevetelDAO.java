package hu.sfm.utils;

import hu.sfm.entity.Bevetel;
import hu.sfm.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public interface BevetelDAO extends AutoCloseable{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void saveBevetel( Bevetel b);

    public void deleteBevetel(Bevetel b);

    public void updateBevetel(Bevetel b);

    public List<Bevetel> getBevetelek();
}
