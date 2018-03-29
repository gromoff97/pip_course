package course.service;


import course.entity.EntityPermissionTypes;

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
 * Service class implementing CRUD Api for {@link EntityPermissionTypes}.
 **/

@Stateless
@TransactionManagement
public class PermissionTypesService {
    @PersistenceContext
    private EntityManager em;

    public boolean createType(String typeName){
        EntityPermissionTypes permType = new EntityPermissionTypes(typeName);
        try {
            em.persist(permType);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteType(EntityPermissionTypes permType){
        try {
            em.remove(permType);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean changePermissionName(EntityPermissionTypes permType, String nowPermName){
        try {
            permType.setName(nowPermName);
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public Collection<EntityPermissionTypes> getPermTypes(){
        return em.createQuery("SELECT pt FROM EntityPermissionTypes pt",EntityPermissionTypes.class).getResultList();
    }

    public EntityPermissionTypes getPermTypeByName(String name){
        return em.createQuery("SELECT pt FROM EntityPermissionTypes pt where pt.name = :name",EntityPermissionTypes.class)
                .setParameter("name",name)
                .getSingleResult();
    }

    public EntityPermissionTypes getPermTypeById(int id){
        return em.find(EntityPermissionTypes.class, id);
    }

}
