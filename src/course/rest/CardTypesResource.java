package course.rest;

import course.entity.EntityCardTypes;
import course.messages.MessageService;
import course.service.CardTypesService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Stateless
@Path("cardtypes")
public class CardTypesResource {
    @EJB
    private CardTypesService cardTypes;

    @EJB
    private MessageService messageService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<EntityCardTypes> getAllCardTypes() {
        return cardTypes.getCardTypes();
    }

    @GET
    @Path("add/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addCardType(@PathParam("name")String name) {
        String msg = "Card type " + name + " was added";
        boolean result = cardTypes.createCardType(name);
        if (result) {
            messageService.sendMsg(msg);
        }
        return result;
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCardType(@PathParam("id")int id) {
        EntityCardTypes cardType = cardTypes.getCardTypeById(id);
        if (cardType == null) {
            return false;
        }
        String msg = "Card type " + cardType.getName() + " was removed";
        boolean result = cardTypes.deleteCardType(cardType);
        if (result) {
            messageService.sendMsg(msg);
        }
        return result;
    }

    @GET
    @Path("change/{id}/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean changeCardTypeName(@PathParam("id")int id, @PathParam("name")String name) {
        EntityCardTypes cardType = cardTypes.getCardTypeById(id);
        if (cardType == null) {
            return false;
        }
        String msg = "Card type " + cardType.getName() + "'s name was changed to " + name;
        boolean result = cardTypes.changeCardTypeName(cardType, name);
        if (result) {
            messageService.sendMsg(msg);
        }
        return result;
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityCardTypes getCardTypeByName(@PathParam("name")String name) {
        return cardTypes.getCardTypeByName(name);
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityCardTypes getCardTypeById(@PathParam("id")int id) {
        return cardTypes.getCardTypeById(id);
    }
}
