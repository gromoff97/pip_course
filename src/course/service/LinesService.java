package course.service;

import course.entity.EntityLines;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityLines}.
 **/

@Stateless
@TransactionManagement
public class LinesService {
    @PersistenceContext
    private EntityManager em;

    public boolean createLine(int number, String color) {
        EntityLines line = new EntityLines(number, color);
        try{
            em.persist(line);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteLine(EntityLines line){
        try {
            em.remove(line);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public Collection<EntityLines> getLines(){
        return em.createQuery("SELECT l FROM EntityLines l",EntityLines.class).getResultList();
    }

    public EntityLines getLineByColor(String color){
        return em.createQuery("SELECT l FROM EntityLines l where l.schemeColor = :color",EntityLines.class)
                .setParameter("color",color)
                .getSingleResult();
    }

    public EntityLines getLineById(int id){
        return em.find(EntityLines.class, id);
    }

}
