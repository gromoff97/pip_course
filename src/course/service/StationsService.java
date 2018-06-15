package course.service;

import course.entity.EntityLines;
import course.entity.EntityStations;
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
 * Service class implementing CRUD Api for {@link EntityStations}.
 **/

@Stateless
@TransactionManagement
public class StationsService {
    @PersistenceContext
    private EntityManager em;

    public boolean createStation(String name, EntityLines line, EntityStationsStates state) {
        EntityStations stations = new EntityStations(name, line, state);
        try {
            em.persist(stations);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteStation(EntityStations station) {
        try {
            em.remove(station);
            em.flush();
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean changeStationName(EntityStations station, String newName){
        try {
            station.setName(newName);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean changeStationState(EntityStations station, EntityStationsStates newState){
        try {
            station.setState(newState);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Collection<EntityStations> getStations() {
        return em.createQuery("SELECT s FROM EntityStations s ORDER BY s.name", EntityStations.class)
                .getResultList();
    }

    public EntityStations getStationByName(String name) {
        try {
            return em.createQuery("SELECT s FROM EntityStations s where s.name = :name", EntityStations.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public EntityStations getStationById(int id){
        return em.find(EntityStations.class, id);
    }
}
