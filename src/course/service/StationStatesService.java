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
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean deleteState(String stateName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityStationsStates WHERE name = :name")
                    .setParameter("name", stateName).executeUpdate();
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
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
        }
        catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public Collection<EntityStationsStates> getStates() {
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT ss FROM EntityStationsStates ss",EntityStationsStates.class).getResultList();
    }

    public EntityStationsStates getStateByName(String name) {
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT ss FROM EntityStationsStates ss where ss.name = :name", EntityStationsStates.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
