package course.rest;

import com.google.gson.Gson;
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

@Stateless
@Path("cards")
public class CardsResource {
    @EJB
    private CardsService cards;

    @EJB
    private UsersService users;

    @EJB
    private CardTypesService cardTypes;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCards() {
        Gson gson = new Gson();
        return gson.toJson(cards.getCards());
    }

    @GET
    @Path("add/{user}/{type}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean addCard(@PathParam("user")int user, @PathParam("type")int type) {
        return cards.createCard(users.getUserById(user), cardTypes.getCardTypeById(type));
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deleteCard(@PathParam("id")int id) {
        return cards.deleteCard(cards.getCardsById(id));
    }

    @GET
    @Path("change/{id}/type/{type}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changeCardType(@PathParam("id")int id, @PathParam("type")int type) {
        return cards.changeCardType(cards.getCardsById(id), cardTypes.getCardTypeById(type));
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCardById(@PathParam("id")int id) {
        Gson gson = new Gson();
        return gson.toJson(cards.getCardsById(id));
    }
}
