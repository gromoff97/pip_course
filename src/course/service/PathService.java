package course.service;

import course.entity.EntityPath;
import course.entity.EntityStations;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityPath}.
 **/

@Stateless
@TransactionManagement
public class PathService {
    @PersistenceContext
    private EntityManager em;

    public boolean createPath(EntityStations fromStation, EntityStations toStation, int timeInSec) {
        if (getPathFromTo(fromStation, toStation) != null) {
            return false;
        }
        EntityPath path = new EntityPath(fromStation, toStation, timeInSec);
        try {
            em.persist(path);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deletePath(EntityPath path){
        try {
            em.remove(path);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean changePathTime(EntityPath path, int newTimeInSec){
        try {
            path.setTimeInSec(newTimeInSec);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public EntityPath getPathFromTo(EntityStations fromSt, EntityStations toSt) {
        EntityPath result;
        try {
            result = em.createQuery("SELECT p FROM EntityPath p where p.fromStation = :st1 and p.toStation = :st2", EntityPath.class)
                    .setParameter("st1", fromSt)
                    .setParameter("st2", toSt)
                    .getSingleResult();
        } catch (Exception e1) {
            try {
                result = em.createQuery("SELECT p FROM EntityPath p where p.fromStation = :st1 and p.toStation = :st2", EntityPath.class)
                        .setParameter("st1", toSt)
                        .setParameter("st2", fromSt)
                        .getSingleResult();
            } catch (Exception e2) {
                result = null;
            }
        }
        return result;
    }

    public EntityPath getPathById(int id){
        return em.find(EntityPath.class, id);
    }

}
