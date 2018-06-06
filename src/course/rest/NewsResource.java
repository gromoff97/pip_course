package course.rest;

import course.entity.EntityNews;
import course.messages.MessageService;
import course.service.NewsService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Stateless
@Path("news")
public class NewsResource {
    @EJB
    private NewsService news;

    @EJB
    private MessageService messageService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<EntityNews> getAllPosts() {
        return news.getPosts();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addPost(String content) {
        String msg = "New post: '" + content + "'";
        boolean res = news.createPost(content);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deletePost(@PathParam("id")int id) {
        EntityNews post = news.getPostById(id);
        if (post == null) {
            return false;
        }
        String msg = "Post '" + post.getContent() + "' was removed";
        boolean res = news.deletePost(post);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @POST
    @Path("change/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean changePostContent(@PathParam("id")int id, String content) {
        EntityNews post = news.getPostById(id);
        if (post == null) {
            return false;
        }
        String msg = "Post was changed from '" + post.getContent() + "' to '" + content + "'";
        boolean res = news.changePostContent(post, content);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityNews getPostById(@PathParam("id")int id) {
        return news.getPostById(id);
    }
}
