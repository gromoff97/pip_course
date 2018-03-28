package course.service;

import course.entity.EntityLines;
import course.entity.EntityStationsStates;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityStationsStates}.
 **/

public class StationStatesService {

    public boolean createState(String stateName){
        EntityManager em = EntityService.getEntityManager();
        EntityStationsStates state = new EntityStationsStates(stateName);
        em.getTransaction().begin();
        try {
            em.persist(state);
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

    public boolean deleteState(String stateName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityStationsStates WHERE name = :name")
                    .setParameter("name", stateName).executeUpdate();
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

    public boolean changeStateName(int stateId, String nowStateName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try{
        em.createQuery("SELECT t FROM EntityStationsStates t WHERE t.id = :stateId", EntityStationsStates.class)
                .setParameter("stateId",stateId)
                .getSingleResult()
                .setName(nowStateName);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public Collection<EntityStationsStates> getStates() {
        EntityManager em = EntityService.getEntityManager();
        Collection<EntityStationsStates> result = em.createQuery("SELECT ss FROM EntityStationsStates ss",EntityStationsStates.class).getResultList();
        em.close();
        return result;
    }

    public EntityStationsStates getStateByName(String name) {
        EntityManager em = EntityService.getEntityManager();
        EntityStationsStates result = em.createQuery("SELECT ss FROM EntityStationsStates ss where ss.name = :name", EntityStationsStates.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return result;
    }

}
