package course.service;

import course.entity.EntityPath;
import course.entity.EntityStations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityPath}.
 **/

@Stateless
public class PathService {

    public boolean createPath(EntityStations fromStation, EntityStations toStation, int timeInSec){
        EntityManager em = EntityService.getEntityManager();
        EntityPath path = new EntityPath(fromStation, toStation, timeInSec);
        em.getTransaction().begin();
        try {
            em.persist(path);
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

    public boolean deletePath(EntityPath path){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(path);
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

    public boolean changePathTime(EntityPath path, int newTimeInSec){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            path.setTimeInSec(newTimeInSec);
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

    public EntityPath getPathFromTo(EntityStations fromSt, EntityStations toSt) {
        EntityManager em = EntityService.getEntityManager();
        EntityPath result = em.createQuery("SELECT p FROM EntityPath p where p.fromStation = :st1 and p.toStation = :st2", EntityPath.class)
                .setParameter("st1", fromSt)
                .setParameter("st2", toSt)
                .getSingleResult();
        em.close();
        return result;
    }

    public EntityPath getPathById(int id){
        EntityManager em = EntityService.getEntityManager();
        EntityPath result = em.find(EntityPath.class, id);
        em.close();
        return result;
    }

}
