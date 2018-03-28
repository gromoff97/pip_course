package course.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityService {
    private static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("course.database");
    }

    private EntityService() {}

    public synchronized static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
