package course.service;

import course.entity.EntityLines;
import course.entity.EntityStations;
import course.entity.EntityStationsStates;

import javax.persistence.EntityManager;
import java.util.Collection;

public class StationsService {

    public boolean createStation(String name, EntityLines line, EntityStationsStates state) {
        EntityManager em = EntityService.getEntityManager();
        EntityStations stations = new EntityStations(name, line, state);
        em.getTransaction().begin();
        try {
            em.persist(stations);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean deleteStation(int id) {
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityStations WHERE id = :id")
                    .setParameter("id", id).executeUpdate();
        } catch (Exception e){
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean changeStationName(int id, String newName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT s FROM EntityStations s WHERE s.id = :id", EntityStations.class)
                    .setParameter("id", id).getSingleResult().setName(newName);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean changeStationState(int id, EntityStationsStates newState){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT s FROM EntityStations s WHERE s.id = :id", EntityStations.class)
                    .setParameter("id", id).getSingleResult().setState(newState);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public Collection<EntityStations> getStations() {
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT s FROM EntityStations s", EntityStations.class)
                .getResultList();
    }

    public EntityStations getStationByName(String name) {
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT s FROM EntityStations s where s.name = :name", EntityStations.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
