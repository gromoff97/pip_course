package course.service;

import com.google.gson.Gson;
import course.entity.EntityLines;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityLines}.
 **/

public class LinesService {

    public boolean createLine(int number, String color) {
        EntityManager em = EntityService.getEntityManager();
        EntityLines line = new EntityLines(number, color);
        em.getTransaction().begin();
        try{
            em.persist(line);
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

    public boolean deleteLine(int lineId){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityLines WHERE number = :number")
                    .setParameter("number", lineId).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e){
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public Collection<EntityLines> getLines(){
        EntityManager em = EntityService.getEntityManager();
        Collection<EntityLines> result = em.createQuery("SELECT l FROM EntityLines l",EntityLines.class).getResultList();
        em.close();
        return result;
    }

    public EntityLines getLineByColor(String color){
        EntityManager em = EntityService.getEntityManager();
        EntityLines result = em.createQuery("SELECT l FROM EntityLines l where l.schemeColor = :color",EntityLines.class)
                .setParameter("color",color)
                .getSingleResult();
        em.close();
        return result;
    }

}
