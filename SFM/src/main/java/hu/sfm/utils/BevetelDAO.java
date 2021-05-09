package hu.sfm.utils;

import hu.sfm.entity.Bevetel;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public interface BevetelDAO extends AutoCloseable{
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    void saveBevetel( Bevetel b);

    void deleteBevetel(Bevetel b);

    void updateBevetel(Bevetel b);


    List<Bevetel> getBevetelek();
}
