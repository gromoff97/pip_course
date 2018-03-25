package course.service;

import com.sun.xml.wss.impl.c14n.EXC14nStAXReaderBasedCanonicalizer;
import course.entity.EntityCardTypes;
import course.entity.EntityNews;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Collection;

public class CardTypesService {

    public boolean createCardType(String name) {
        EntityManager em = EntityService.getEntityManager();
        EntityCardTypes cardType = new EntityCardTypes(name);
        em.getTransaction().begin();
        try {
            em.persist(cardType);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean deleteCardType(int id){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityCardTypes WHERE id = :id")
                    .setParameter("id", id).executeUpdate();
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean changeCardTypeName(int id, String newName) {
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT t FROM EntityCardTypes t WHERE t.id = :id", EntityCardTypes.class)
                    .setParameter("id", id).getSingleResult().setName(newName);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public Collection<EntityCardTypes> getCardTypes(){
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT ct FROM EntityCardTypes ct",EntityCardTypes.class).getResultList();
    }

    public EntityCardTypes getCardTypeByName(String name){
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT ct FROM EntityCardTypes ct where ct.name = :name",EntityCardTypes.class)
                .setParameter("name",name)
                .getSingleResult();
    }
}
