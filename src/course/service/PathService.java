package course.service;

import course.entity.EntityPath;
import course.entity.EntityStations;

import javax.persistence.EntityManager;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityPath}.
 **/

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

    public boolean deletePath(int id){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityPath WHERE id = :id")
                    .setParameter("id", id).executeUpdate();
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

    public boolean changePathTime(int id, int newTimeInSec){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT p FROM EntityPath p WHERE p.id = :id", EntityPath.class)
                    .setParameter("id", id).getSingleResult().setTimeInSec(newTimeInSec);
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
        EntityPath result = em.createQuery("SELECT p FROM EntityPath p where p.id = :id", EntityPath.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return result;
    }

}
