package course.service;

import course.entity.EntityPermissionTypes;
import course.entity.EntityUsers;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Collection;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityUsers}.
 **/

public class UsersService {

    public boolean createUser(String firstName, String lastName, Date birthDate, String eMail, EntityPermissionTypes userPermissionType){
        EntityManager em = EntityService.getEntityManager();
        EntityUsers user = new EntityUsers(firstName,lastName,birthDate,eMail,userPermissionType);
        em.getTransaction().begin();
        try{
            em.persist(user);
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

    public boolean deleteUser(int id){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityUsers u WHERE u.id = :id")
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

    public boolean changeUserEmail(int userId, String nowUserEmail){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT u FROM EntityUsers u WHERE u.id = :id", EntityUsers.class)
                    .setParameter("id",userId)
                    .getSingleResult()
                    .seteMail(nowUserEmail);
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

    public boolean changeUserFirstName(int userId, String nowFirstName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT u FROM EntityUsers u WHERE u.id = :id", EntityUsers.class)
                    .setParameter("id",userId)
                    .getSingleResult()
                    .setFirstName(nowFirstName);
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

    public boolean changeUserLastName(int userId, String nowLastName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT u FROM EntityUsers u WHERE u.id = :id", EntityUsers.class)
                    .setParameter("id",userId)
                    .getSingleResult()
                    .setLastName(nowLastName);
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

    public boolean changeUserBalance(int userId, int nowBalance){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT u FROM EntityUsers u WHERE u.id = :id", EntityUsers.class)
                    .setParameter("id",userId)
                    .getSingleResult()
                    .setBalance(nowBalance);
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

    public Collection<EntityUsers> getUsers() {
        EntityManager em = EntityService.getEntityManager();
        Collection<EntityUsers> result = em.createQuery("SELECT l FROM EntityUsers l",EntityUsers.class).getResultList();
        em.close();
        return result;
    }

    public EntityUsers getUserByEmail(String Email){
        EntityManager em = EntityService.getEntityManager();
        EntityUsers result = em.createQuery("SELECT l FROM EntityUsers l where l.eMail = :mail",EntityUsers.class)
                .setParameter("mail",Email)
                .getSingleResult();
        em.close();
        return result;
    }

    public int getBalanceById(int id){
        return getUserById(id).getBalance();
    }

    public EntityUsers getUserById(int id){
        EntityManager em = EntityService.getEntityManager();
        EntityUsers result = em.createQuery("SELECT u FROM EntityUsers u where u.id = :id", EntityUsers.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return result;
    }
}
