package course.service;

import course.entity.EntityNews;

import javax.persistence.EntityManager;
import java.sql.Date;

public class NewsService {

    public void createPost(String content) {
        EntityManager em = EntityService.getEntityManager();
        Date date = new Date(new java.util.Date().getTime());
        EntityNews post = new EntityNews(content, date);
        em.getTransaction().begin();
        em.persist(post);
        em.getTransaction().commit();
        em.close();
    }

    public void deletePost(int id){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM EntityNews WHERE id = :id")
                .setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void changePostContent(int id, String newContent) {
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        EntityNews post = em.createQuery("SELECT n FROM EntityNews n WHERE n.id = :id", EntityNews.class)
                .setParameter("id", id).getSingleResult();
        post.setContent(newContent);
        em.getTransaction().commit();
        em.close();
    }
}
