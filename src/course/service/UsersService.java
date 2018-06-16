package course.service;

import course.entity.EntityPermissionTypes;
import course.entity.EntityUsers;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

@Stateless
@TransactionManagement
public class UsersService {
    @PersistenceContext
    private EntityManager em;

    public boolean createUser(String login, String firstName, String lastName, Date birthDate, String eMail, EntityPermissionTypes userPermissionType){
        EntityUsers user = new EntityUsers(login, firstName,lastName,birthDate,eMail,userPermissionType);
        try{
            em.persist(user);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteUser(EntityUsers user){
        try {
            em.remove(user);
            em.flush();
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean changeUserEmail(EntityUsers user, String nowUserEmail){
        try {
            user.seteMail(nowUserEmail);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean changeUserFirstName(EntityUsers user, String nowFirstName){
        try {
            user.setFirstName(nowFirstName);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean changeUserLastName(EntityUsers user, String nowLastName){
        try {
            user.setLastName(nowLastName);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean changeUserBalance(EntityUsers user, int nowBalance){
        try {
            user.setBalance(nowBalance);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Collection<EntityUsers> getUsers() {
        return em.createQuery("SELECT l FROM EntityUsers l",EntityUsers.class).getResultList();
    }

    public EntityUsers getUserByEmail(String Email) {
        try {
            return em.createQuery("SELECT l FROM EntityUsers l where l.eMail = :mail",EntityUsers.class)
                    .setParameter("mail",Email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public EntityUsers getUserByLogin(String login) {
        try {
            return em.createQuery("SELECT l FROM EntityUsers l where l.login = :login",EntityUsers.class)
                    .setParameter("login",login)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public EntityUsers getUserById(int id) {
        return em.find(EntityUsers.class, id);
    }

    public int getBalanceById(int id) throws NullPointerException {
        return getUserById(id).getBalance();
    }
}
