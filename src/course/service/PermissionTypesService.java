package course.service;


import course.entity.EntityPermissionTypes;

import javax.persistence.EntityManager;
import java.util.Collection;

public class PermissionTypesService {

    public boolean createType(String typeName){
        EntityManager em = EntityService.getEntityManager();
        EntityPermissionTypes permType = new EntityPermissionTypes(typeName);
        em.getTransaction().begin();
        try {
            em.persist(permType);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean deleteType(String typeName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityPermissionTypes WHERE name = :name")
                    .setParameter("name",typeName).executeUpdate();
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
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
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return false;
    }

    public Collection<EntityPermissionTypes> getPermTypes(){
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT pt FROM EntityPermissionTypes pt",EntityPermissionTypes.class).getResultList();
    }

    public EntityPermissionTypes getPermTypeByName(String name){
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT pt FROM EntityPermissionTypes pt where pt.name = :name",EntityPermissionTypes.class)
                .setParameter("name",name)
                .getSingleResult();
    }

}
