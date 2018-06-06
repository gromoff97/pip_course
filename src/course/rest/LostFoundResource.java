package course.rest;

import course.entity.EntityLostFound;
import course.messages.MessageService;
import course.service.LostFoundService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

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
    public Collection<EntityLostFound> getAllMessages() {
        return lostFound.getMessages();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteMessage(@PathParam("id")int id) {
        EntityLostFound message = lostFound.getMessageById(id);
        if (message == null) {
            return false;
        }
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
    public EntityLostFound getMessageById(@PathParam("id")int id) {
        return lostFound.getMessageById(id);
    }
}
