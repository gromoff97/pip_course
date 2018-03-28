package course.service;

import course.entity.EntityLostFound;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Collection;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityLostFound}.
 **/

@Stateless
public class LostFoundService {

    public boolean createMessage(String message) {
        EntityManager em = EntityService.getEntityManager();
        Date date = new Date(new java.util.Date().getTime());
        EntityLostFound lostFound = new EntityLostFound(message, date);
        em.getTransaction().begin();
        try {
            em.persist(lostFound);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public boolean deleteMessage(EntityLostFound message){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(message);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public Collection<EntityLostFound> getMessages(){
        EntityManager em = EntityService.getEntityManager();
        Collection<EntityLostFound> result = em.createQuery("SELECT lf FROM EntityLostFound lf",EntityLostFound.class).getResultList();
        em.close();
        return result;
    }

    public EntityLostFound getMessageById(int id){
        EntityManager em = EntityService.getEntityManager();
        EntityLostFound result = em.find(EntityLostFound.class, id);
        em.close();
        return result;
    }
}
