package course.rest;

import com.google.gson.Gson;
import course.entity.EntityLostFound;
import course.messages.MessageService;
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

    @EJB
    private MessageService messageService;

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
        String msg = "New message: '" + content + "'";
        boolean res = lostFound.createMessage(content);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deleteMessage(@PathParam("id")int id) {
        EntityLostFound message = lostFound.getMessageById(id);
        String msg = "Message '" + message.getMessage() + "' was removed";
        boolean res = lostFound.deleteMessage(message);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessageById(@PathParam("id")int id) {
        Gson gson = new Gson();
        return gson.toJson(lostFound.getMessageById(id));
    }
}
