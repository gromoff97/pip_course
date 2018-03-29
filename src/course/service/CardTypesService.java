package course.service;

import course.entity.EntityCardTypes;

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
 * Service class implementing CRUD Api for {@link EntityCardTypes}.
 **/

@Stateless
@TransactionManagement
public class CardTypesService {
    @PersistenceContext
    private EntityManager em;

    public boolean createCardType(String name) {
        EntityCardTypes cardType = new EntityCardTypes(name);
        try {
            em.persist(cardType);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteCardType(EntityCardTypes cardType){
        try {
            em.remove(cardType);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean changeCardTypeName(EntityCardTypes cardType, String newName) {
        try {
            cardType.setName(newName);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Collection<EntityCardTypes> getCardTypes(){
        return em.createQuery("SELECT ct FROM EntityCardTypes ct",EntityCardTypes.class).getResultList();
    }

    public EntityCardTypes getCardTypeByName(String name){
        return em.createQuery("SELECT ct FROM EntityCardTypes ct where ct.name = :name",EntityCardTypes.class)
                .setParameter("name",name)
                .getSingleResult();
    }

    public EntityCardTypes getCardTypeById(int id){
        return em.find(EntityCardTypes.class, id);
    }
}
