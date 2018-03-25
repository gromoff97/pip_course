package course.service;

import course.entity.EntityCardTypes;
import course.entity.EntityNews;

import javax.persistence.EntityManager;
import java.sql.Date;

public class CardTypesService {

    public void createCardType(String name) {
        EntityManager em = EntityService.getEntityManager();
        EntityCardTypes cardType = new EntityCardTypes(name);
        em.getTransaction().begin();
        em.persist(cardType);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteCardType(int id){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM EntityCardTypes WHERE id = :id")
                .setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void changeCardTypeName(int id, String newName) {
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        EntityCardTypes cardType = em.createQuery("SELECT t FROM EntityCardTypes t WHERE t.id = :id", EntityCardTypes.class)
                .setParameter("id", id).getSingleResult();
        cardType.setName(newName);
        em.getTransaction().commit();
        em.close();
    }
}
