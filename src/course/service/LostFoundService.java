package course.service;

import course.entity.EntityLostFound;
import org.apache.tools.ant.taskdefs.EchoXML;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Collection;

public class LostFoundService {

    public boolean createMessage(String message) {
        EntityManager em = EntityService.getEntityManager();
        Date date = new Date(new java.util.Date().getTime());
        EntityLostFound lostFound = new EntityLostFound(message, date);
        em.getTransaction().begin();
        try {
            em.persist(lostFound);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean deleteMessage(int id){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityLostFound WHERE id = :id")
                .setParameter("id", id).executeUpdate();
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public Collection<EntityLostFound> getMessages(){
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT lf FROM EntityLostFound lf",EntityLostFound.class).getResultList();
    }
}
