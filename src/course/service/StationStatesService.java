package course.service;

import course.entity.EntityStationsStates;

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
 * Service class implementing CRUD Api for {@link EntityStationsStates}.
 **/

@Stateless
@TransactionManagement
public class StationStatesService {
    @PersistenceContext
    private EntityManager em;

    public boolean createState(String stateName){
        EntityStationsStates state = new EntityStationsStates(stateName);
        try {
            em.persist(state);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteState(EntityStationsStates state){
        try {
            em.remove(state);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean changeStateName(EntityStationsStates state, String nowStateName){
        try{
            state.setName(nowStateName);
            em.flush();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public Collection<EntityStationsStates> getStates() {
        return em.createQuery("SELECT ss FROM EntityStationsStates ss",EntityStationsStates.class).getResultList();
    }

    public EntityStationsStates getStateByName(String name) {
        try {
            return em.createQuery("SELECT ss FROM EntityStationsStates ss where ss.name = :name", EntityStationsStates.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public EntityStationsStates getStateById(int id){
        return em.find(EntityStationsStates.class, id);
    }

}
