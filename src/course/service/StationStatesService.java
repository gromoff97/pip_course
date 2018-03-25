package course.service;

import course.entity.EntityLines;
import course.entity.EntityStationsStates;

import javax.persistence.EntityManager;

public class StationStatesService {

    public void createState(String stateName){
        EntityManager em = EntityService.getEntityManager();
        EntityStationsStates state = new EntityStationsStates(stateName);
        em.getTransaction().begin();
        em.persist(state);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteState(String stateName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM EntityStationsStates WHERE name = :name")
                .setParameter("name",stateName);
        em.getTransaction().commit();
        em.close();
    }

    public void changeStateName(String prevStateName, String nowStateName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT t FROM EntityStationsStates t WHERE name = :prevName", EntityStationsStates.class)
                .setParameter("prevName",prevStateName)
                .getSingleResult()
                .setName(nowStateName);
        em.getTransaction().commit();
        em.close();
    }

}
