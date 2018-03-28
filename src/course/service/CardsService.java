package course.service;


import course.entity.EntityCardTypes;
import course.entity.EntityCards;
import course.entity.EntityUsers;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityCards}.
 **/

public class CardsService {

    public boolean createCard(EntityUsers user, EntityCardTypes type) {
        EntityManager em = EntityService.getEntityManager();
        EntityCards card = new EntityCards(user, type);
        em.getTransaction().begin();
        try {
            em.persist(card);
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

    public boolean deleteCard(EntityCards card) {
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(card);
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

    public boolean changeCardType(EntityCards card, EntityCardTypes newType) {
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            card.setCardType(newType);
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

    public Collection<EntityCards> getCards() {
        EntityManager em = EntityService.getEntityManager();
        Collection<EntityCards> result = em.createQuery("SELECT c FROM EntityCards c", EntityCards.class)
                .getResultList();
        em.close();
        return result;
    }

    public EntityCards getCardsById(int id){
        EntityManager em = EntityService.getEntityManager();
        EntityCards result = em.find(EntityCards.class, id);
        em.close();
        return result;
    }
}
