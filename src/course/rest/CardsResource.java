package course.rest;

import course.entity.EntityCardTypes;
import course.entity.EntityCards;
import course.entity.EntityUsers;
import course.messages.MessageService;
import course.service.CardTypesService;
import course.service.CardsService;
import course.service.UsersService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Stateless
@Path("cards")
public class CardsResource {
    @EJB
    private CardsService cards;

    @EJB
    private UsersService users;

    @EJB
    private CardTypesService cardTypes;

    @EJB
    private MessageService messageService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<EntityCards> getAllCards() {
        return cards.getCards();
    }

    @GET
    @Path("add/{user}/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addCard(@PathParam("user")int userId, @PathParam("type")int type) {
        EntityUsers user = users.getUserById(userId);
        if (user == null) {
            return false;
        }
        String msg = user.getLastName() + " " + user.getFirstName() + "'s card was added";
        boolean result = cards.createCard(user, cardTypes.getCardTypeById(type));
        if (result) {
            messageService.sendMsg(msg);
        }
        return result;
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCard(@PathParam("id")int id) {
        EntityCards card = cards.getCardsById(id);
        if (card == null) {
            return false;
        }
        String msg = card.getUser().getLastName() + " " + card.getUser().getFirstName() + "'s card was removed";
        boolean result = cards.deleteCard(card);
        if (result) {
            messageService.sendMsg(msg);
        }
        return result;
    }

    @GET
    @Path("change/{id}/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean changeCardType(@PathParam("id")int id, @PathParam("type")int type) {
        EntityCardTypes newType = cardTypes.getCardTypeById(type);
        if (newType == null) {
            return false;
        }
        String msg = "Type of card " + id + " was changed to " + newType.getName();
        boolean result = cards.changeCardType(cards.getCardsById(id), newType);
        if (result) {
            messageService.sendMsg(msg);
        }
        return result;
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityCards getCardById(@PathParam("id")int id) {
        return cards.getCardsById(id);
    }
}
