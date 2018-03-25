package course.service;

import course.entity.EntityPermissionTypes;
import course.entity.EntityUsers;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Collection;

public class UsersService {

    public boolean createUser(String firstName, String lastName, Date birthDate, String eMail, EntityPermissionTypes userPermissionType){
        EntityManager em = EntityService.getEntityManager();
        EntityUsers user = new EntityUsers(firstName,lastName,birthDate,eMail,userPermissionType);
        em.getTransaction().begin();
        try{
            em.persist(user);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean deleteUser(int id){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityUsers u WHERE u.id = :id")
                    .setParameter("id", id).executeUpdate();
        } catch (Exception e){
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean changeUserEmail(int userId, String nowUserEmail){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT u FROM EntityUsers u WHERE u.id = :id", EntityUsers.class)
                    .setParameter("id",userId)
                    .getSingleResult()
                    .seteMail(nowUserEmail);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return false;
    }

    public boolean changeUserFirstName(int userId, String nowFirstName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT u FROM EntityUsers u WHERE u.id = :id", EntityUsers.class)
                    .setParameter("id",userId)
                    .getSingleResult()
                    .setFirstName(nowFirstName);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return false;
    }

    public boolean changeUserLastName(int userId, String nowLastName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT u FROM EntityUsers u WHERE u.id = :id", EntityUsers.class)
                    .setParameter("id",userId)
                    .getSingleResult()
                    .setLastName(nowLastName);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return false;
    }

    public boolean changeUserBalance(int userId, int nowBalance){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT u FROM EntityUsers u WHERE u.id = :id", EntityUsers.class)
                    .setParameter("id",userId)
                    .getSingleResult()
                    .setBalance(nowBalance);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return false;
    }

    public Collection<EntityUsers> getUsers() {
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT l FROM EntityUsers l",EntityUsers.class).getResultList();
    }

    public EntityUsers getUserByEmail(String Email){
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT l FROM EntityUsers l where l.eMail = :mail",EntityUsers.class)
                .setParameter("mail",Email)
                .getSingleResult();
    }

    public int getBalanceById(int id){
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT l.balance FROM EntityUsers l where l.id = :id",int.class)
                .setParameter("id",id)
                .getSingleResult();
    }


}
