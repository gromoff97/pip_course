package course.service;

import course.entity.EntityLostFound;

import javax.persistence.EntityManager;
import java.sql.Date;

public class LostFoundService {

    public void createMessage(String message) {
        EntityManager em = EntityService.getEntityManager();
        Date date = new Date(new java.util.Date().getTime());
        EntityLostFound lostFound = new EntityLostFound(message, date);
        em.getTransaction().begin();
        em.persist(lostFound);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteMessage(int id){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM EntityLostFound WHERE id = :id")
                .setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
