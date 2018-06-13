package course.rest;

import course.messages.MessageService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("queue")
public class QueueResource {

    @EJB
    private MessageService messageService;

    @GET
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCards() {
        return messageService.createQueue();
    }
}
