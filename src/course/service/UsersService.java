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

    public boolean deleteUser(EntityUsers user){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(user);
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

    public boolean changeUserEmail(EntityUsers user, String nowUserEmail){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            user.seteMail(nowUserEmail);
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

    public boolean changeUserFirstName(EntityUsers user, String nowFirstName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            user.setFirstName(nowFirstName);
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

    public boolean changeUserLastName(EntityUsers user, String nowLastName){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            user.setLastName(nowLastName);
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

    public boolean changeUserBalance(EntityUsers user, int nowBalance){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            user.setBalance(nowBalance);
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

    public EntityUsers getUserById(int id){
        EntityManager em = EntityService.getEntityManager();
        EntityUsers result = em.find(EntityUsers.class, id);
        em.close();
        return result;
    }

    public int getBalanceById(int id){
        return getUserById(id).getBalance();
    }
}
