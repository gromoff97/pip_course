package course.rest;

import com.google.gson.Gson;
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
        return news.createPost(content);
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deletePost(@PathParam("id")int id) {
        return news.deletePost(news.getPostById(id));
    }

    @POST
    @Path("change/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changePostContent(@PathParam("id")int id, String content) {
        return news.changePostContent(news.getPostById(id), content);
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPostById(@PathParam("id")int id) {
        Gson gson = new Gson();
        return gson.toJson(news.getPostById(id));
    }
}
