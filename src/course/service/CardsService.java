package course.service;


import course.entity.EntityCardTypes;
import course.entity.EntityCards;
import course.entity.EntityUsers;

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
 * Service class implementing CRUD Api for {@link EntityCards}.
 **/

@Stateless
@TransactionManagement
public class CardsService {
    @PersistenceContext
    private EntityManager em;

    public boolean createCard(EntityUsers user, EntityCardTypes type) {
        EntityCards card = new EntityCards(user, type);
        try {
            em.persist(card);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteCard(EntityCards card) {
        try {
            em.remove(card);
            em.flush();
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean changeCardType(EntityCards card, EntityCardTypes newType) {
        try {
            card.setCardType(newType);
            em.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Collection<EntityCards> getCards() {
        return em.createQuery("SELECT c FROM EntityCards c", EntityCards.class)
                .getResultList();
    }

    public EntityCards getCardsById(int id){
        return em.find(EntityCards.class, id);
    }
}
