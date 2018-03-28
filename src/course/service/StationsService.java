package course.service;

import course.entity.EntityLines;
import course.entity.EntityStations;
import course.entity.EntityStationsStates;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityStations}.
 **/

public class StationsService {

    public boolean createStation(String name, EntityLines line, EntityStationsStates state) {
        EntityManager em = EntityService.getEntityManager();
        EntityStations stations = new EntityStations(name, line, state);
        em.getTransaction().begin();
        try {
            em.persist(stations);
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

    public boolean deleteStation(int id) {
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityStations WHERE id = :id")
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

    public boolean changeStationName(int id, String newName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT s FROM EntityStations s WHERE s.id = :id", EntityStations.class)
                    .setParameter("id", id).getSingleResult().setName(newName);
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

    public boolean changeStationState(int id, EntityStationsStates newState){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT s FROM EntityStations s WHERE s.id = :id", EntityStations.class)
                    .setParameter("id", id).getSingleResult().setState(newState);
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

    public Collection<EntityStations> getStations() {
        EntityManager em = EntityService.getEntityManager();
        Collection<EntityStations> result = em.createQuery("SELECT s FROM EntityStations s", EntityStations.class)
                .getResultList();
        em.close();
        return result;
    }

    public EntityStations getStationByName(String name) {
        EntityManager em = EntityService.getEntityManager();
        EntityStations result = em.createQuery("SELECT s FROM EntityStations s where s.name = :name", EntityStations.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return result;
    }
}
