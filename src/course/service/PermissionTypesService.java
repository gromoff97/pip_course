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

    public boolean deleteType(EntityPermissionTypes permType){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(permType);
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

    public boolean changePermissionName(EntityPermissionTypes permType, String nowPermName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            permType.setName(nowPermName);
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
        EntityPermissionTypes result = em.find(EntityPermissionTypes.class, id);
        em.close();
        return result;
    }

}
