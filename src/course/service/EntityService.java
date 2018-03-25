package course.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityService {
    private static EntityManagerFactory entityManagerFactory;

    private EntityService() {
        entityManagerFactory = Persistence.createEntityManagerFactory("course.database");
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
