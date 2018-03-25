package course.service;


import course.entity.EntityPermissionTypes;

import javax.persistence.EntityManager;

public class PermissionTypesService {

    public void createType(String typeName){
        EntityManager em = EntityService.getEntityManager();
        EntityPermissionTypes permType = new EntityPermissionTypes(typeName);
        em.getTransaction().begin();
        em.persist(permType);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteType(String typeName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM EntityPermissionTypes WHERE name = :name")
                .setParameter("name",typeName).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void changeStateName(String prevTypeName, String nowTypeName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT t FROM EntityStationsStates t WHERE name = :prevName", EntityPermissionTypes.class)
                .setParameter("prevName",prevTypeName)
                .getSingleResult()
                .setName(nowTypeName);
        em.getTransaction().commit();
        em.close();
    }

}
