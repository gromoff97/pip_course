package course.service;

import course.entity.EntityCardTypes;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityCardTypes}.
 **/

@Stateless
public class CardTypesService {

    public boolean createCardType(String name) {
        EntityManager em = EntityService.getEntityManager();
        EntityCardTypes cardType = new EntityCardTypes(name);
        em.getTransaction().begin();
        try {
            em.persist(cardType);
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

    public boolean deleteCardType(EntityCardTypes cardType){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(cardType);
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

    public boolean changeCardTypeName(EntityCardTypes cardType, String newName) {
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            cardType.setName(newName);
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

    public Collection<EntityCardTypes> getCardTypes(){
        EntityManager em = EntityService.getEntityManager();
        Collection<EntityCardTypes> result = em.createQuery("SELECT ct FROM EntityCardTypes ct",EntityCardTypes.class).getResultList();
        em.close();
        return result;
    }

    public EntityCardTypes getCardTypeByName(String name){
        EntityManager em = EntityService.getEntityManager();
        EntityCardTypes result = em.createQuery("SELECT ct FROM EntityCardTypes ct where ct.name = :name",EntityCardTypes.class)
                .setParameter("name",name)
                .getSingleResult();
        em.close();
        return result;
    }

    public EntityCardTypes getCardTypeById(int id){
        EntityManager em = EntityService.getEntityManager();
        EntityCardTypes result = em.find(EntityCardTypes.class, id);
        em.close();
        return result;
    }
}
