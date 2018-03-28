package course.service;

import course.entity.EntityNews;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Collection;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityService}.
 **/

public class NewsService {

    public boolean createPost(String content) {
        EntityManager em = EntityService.getEntityManager();
        Date date = new Date(new java.util.Date().getTime());
        EntityNews post = new EntityNews(content, date);
        em.getTransaction().begin();
        try {
            em.persist(post);
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

    public boolean deletePost(EntityNews post){
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(post);
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

    public boolean changePostContent(EntityNews post, String newContent) {
        EntityManager em = EntityService.getEntityManager();
        em.getTransaction().begin();
        try {
            post.setContent(newContent);
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

    public Collection<EntityNews> getPosts(){
        EntityManager em = EntityService.getEntityManager();
        Collection<EntityNews> result = em.createQuery("SELECT n FROM EntityNews n",EntityNews.class).getResultList();
        em.close();
        return result;
    }

    public EntityNews getPostById(int id){
        EntityManager em = EntityService.getEntityManager();
        EntityNews result = em.find(EntityNews.class, id);
        em.close();
        return result;
    }

}
