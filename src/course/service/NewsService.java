package course.service;

import course.entity.EntityNews;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.Collection;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Service class implementing CRUD Api for {@link EntityNews}.
 **/

@Stateless
@TransactionManagement
public class NewsService {
    @PersistenceContext
    private EntityManager em;

    public boolean createPost(String content) {
        Date date = new Date(new java.util.Date().getTime());
        EntityNews post = new EntityNews(content, date);
        try {
            em.persist(post);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deletePost(EntityNews post){
        try {
            em.remove(post);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean changePostContent(EntityNews post, String newContent) {
        try {
            post.setContent(newContent);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Collection<EntityNews> getPosts(){
        return em.createQuery("SELECT n FROM EntityNews n",EntityNews.class).getResultList();
    }

    public EntityNews getPostById(int id){
        return em.find(EntityNews.class, id);
    }

}
