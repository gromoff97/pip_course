package course.service;

import course.entity.EntityNews;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Collection;

public class NewsService {

    public boolean createPost(String content) {
        EntityManager em = EntityService.getEntityManager();
        Date date = new Date(new java.util.Date().getTime());
        EntityNews post = new EntityNews(content, date);
        em.getTransaction().begin();
        try {
            em.persist(post);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean deletePost(int id){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM EntityNews WHERE id = :id")
                .setParameter("id", id).executeUpdate();
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean changePostContent(int id, String newContent) {
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("SELECT n FROM EntityNews n WHERE n.id = :id", EntityNews.class)
                    .setParameter("id", id)
                    .getSingleResult()
                    .setContent(newContent);
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public Collection<EntityNews> getPosts(){
        EntityManager em = EntityService.getEntityManager();
        return em.createQuery("SELECT n FROM EntityNews n",EntityNews.class).getResultList();
    }

}
