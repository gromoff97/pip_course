package course.rest;

import com.google.gson.Gson;
import course.entity.EntityNews;
import course.messages.MessageService;
import course.service.NewsService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    public String getAllPosts() {
        Gson gson = new Gson();
        return gson.toJson(news.getPosts());
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
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
    @Produces(MediaType.TEXT_PLAIN)
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
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
    public String getPostById(@PathParam("id")int id) {
        Gson gson = new Gson();
        return gson.toJson(news.getPostById(id));
    }
}
