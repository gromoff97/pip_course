package course.service;

import course.entity.EntityLines;

import javax.persistence.EntityManager;

public class LinesService {

    public void createLine(int number, String color) {
        EntityManager em = EntityService.getEntityManager();
        EntityLines line = new EntityLines(number, color);
        em.getTransaction().begin();
        em.persist(line);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteLine(int number){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM EntityLines WHERE number = :number")
                .setParameter("number", number).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

}
