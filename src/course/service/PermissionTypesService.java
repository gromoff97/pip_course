package course.service;


import course.entity.EntityPermissionTypes;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityPermissionTypes}.
 **/

public class PermissionTypesService {

    public boolean createType(String typeName){
        EntityManager em = EntityService.getEntityManager();
        EntityPermissionTypes permType = new EntityPermissionTypes(typeName);
        em.getTransaction().begin();
        try {
            em.persist(permType);
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

    public boolean deleteType(String typeName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityPermissionTypes WHERE name = :name")
                    .setParameter("name",typeName).executeUpdate();
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

    public boolean changePermissionName(int permId, String nowPermName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT t FROM EntityStationsStates t WHERE t.id = :id", EntityPermissionTypes.class)
                    .setParameter("id",permId)
                    .getSingleResult()
                    .setName(nowPermName);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return false;
    }

    public Collection<EntityPermissionTypes> getPermTypes(){
        EntityManager em = EntityService.getEntityManager();
        Collection<EntityPermissionTypes> result = em.createQuery("SELECT pt FROM EntityPermissionTypes pt",EntityPermissionTypes.class).getResultList();
        em.close();
        return result;
    }

    public EntityPermissionTypes getPermTypeByName(String name){
        EntityManager em = EntityService.getEntityManager();
        EntityPermissionTypes result = em.createQuery("SELECT pt FROM EntityPermissionTypes pt where pt.name = :name",EntityPermissionTypes.class)
                .setParameter("name",name)
                .getSingleResult();
        em.close();
        return result;
    }

    public EntityPermissionTypes getPermTypeById(int id){
        EntityManager em = EntityService.getEntityManager();
        EntityPermissionTypes result = em.createQuery("SELECT pt FROM EntityPermissionTypes pt where pt.id = :id", EntityPermissionTypes.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return result;
    }

}
