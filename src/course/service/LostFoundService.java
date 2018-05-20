package course.service;

import course.entity.EntityLostFound;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@TransactionManagement
public class LostFoundService {
    @PersistenceContext
    private EntityManager em;

    public boolean createMessage(String message) {
        Date date = new Date(new java.util.Date().getTime());
        EntityLostFound lostFound = new EntityLostFound(message, date);
        try {
            em.persist(lostFound);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteMessage(EntityLostFound message){
        try {
            em.remove(message);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Collection<EntityLostFound> getMessages(){
        return em.createQuery("SELECT lf FROM EntityLostFound lf",EntityLostFound.class).getResultList();
    }

    public EntityLostFound getMessageById(int id){
        return em.find(EntityLostFound.class, id);
    }
}
