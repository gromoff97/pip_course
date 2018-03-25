package course.service;

import course.entity.EntityPath;
import course.entity.EntityStations;

import javax.persistence.EntityManager;

public class PathService {

    public boolean createPath(EntityStations fromStation, EntityStations toStation, int timeInSec){
        EntityManager em = EntityService.getEntityManager();
        EntityPath path = new EntityPath(fromStation, toStation, timeInSec);
        em.getTransaction().begin();
        try {
            em.persist(path);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean deletePath(int id){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityPath WHERE id = :id")
                    .setParameter("id", id).executeUpdate();
        } catch (Exception e){
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean changePathTime(int id, int newTimeInSec){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT p FROM EntityPath p WHERE p.id = :id", EntityPath.class)
                    .setParameter("id", id).getSingleResult().setTimeInSec(newTimeInSec);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public EntityPath getPathFromTo(EntityStations fromSt, EntityStations toSt) {
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT p FROM EntityPath p where p.fromStation = :st1 and p.toStation = :st2", EntityPath.class)
                .setParameter("st1", fromSt)
                .setParameter("st2", toSt)
                .getSingleResult();
    }

}
