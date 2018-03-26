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
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean deleteCard(int cardId) {
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityCards WHERE id = :id")
                    .setParameter("id", cardId).executeUpdate();
        } catch (Exception e){
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean changeCardType(int cardId, EntityCardTypes newType) {
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT c FROM EntityCards c WHERE c.id = :id", EntityCards.class)
                    .setParameter("id", cardId).getSingleResult().setCardType(newType);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public Collection<EntityCards> getCards() {
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT c FROM EntityCards c", EntityCards.class)
                .getResultList();
    }
}
