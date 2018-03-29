package course.rest;

import com.google.gson.Gson;
import course.service.LostFoundService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("lostfound")
public class LostFoundResource {
    @EJB
    private LostFoundService lostFound;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllMessages() {
        Gson gson = new Gson();
        return gson.toJson(lostFound.getMessages());
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean addMessage(String content) {
        return lostFound.createMessage(content);
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deleteMessage(@PathParam("id")int id) {
        return lostFound.deleteMessage(lostFound.getMessageById(id));
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessageById(@PathParam("id")int id) {
        Gson gson = new Gson();
        return gson.toJson(lostFound.getMessageById(id));
    }
}
