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

    public boolean createUser(String firstName, String lastName, Date birthDate, String eMail, EntityPermissionTypes userPermissionType){
        EntityUsers user = new EntityUsers(firstName,lastName,birthDate,eMail,userPermissionType);
        try{
            em.persist(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteUser(EntityUsers user){
        try {
            em.remove(user);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean changeUserEmail(EntityUsers user, String nowUserEmail){
        try {
            user.seteMail(nowUserEmail);
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean changeUserFirstName(EntityUsers user, String nowFirstName){
        try {
            user.setFirstName(nowFirstName);
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean changeUserLastName(EntityUsers user, String nowLastName){
        try {
            user.setLastName(nowLastName);
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean changeUserBalance(EntityUsers user, int nowBalance){
        try {
            user.setBalance(nowBalance);
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public Collection<EntityUsers> getUsers() {
        return em.createQuery("SELECT l FROM EntityUsers l",EntityUsers.class).getResultList();
    }

    public EntityUsers getUserByEmail(String Email){
        return em.createQuery("SELECT l FROM EntityUsers l where l.eMail = :mail",EntityUsers.class)
                .setParameter("mail",Email)
                .getSingleResult();
    }

    public EntityUsers getUserById(int id){
        return em.find(EntityUsers.class, id);
    }

    public int getBalanceById(int id){
        return getUserById(id).getBalance();
    }
}
