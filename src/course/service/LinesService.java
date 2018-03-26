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
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean deleteLine(int lineId){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityLines WHERE number = :number")
                    .setParameter("number", lineId).executeUpdate();
        } catch (Exception e){
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public Collection<EntityLines> getLines(){
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT l FROM EntityLines l",EntityLines.class).getResultList();
    }

    public EntityLines getLineByColor(String color){
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT l FROM EntityLines l where l.schemeColor = :color",EntityLines.class)
                .setParameter("color",color)
                .getSingleResult();
    }

}
