package course.rest;

import com.google.gson.Gson;
import course.service.CardTypesService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("cardtypes")
public class CardTypesResource {
    @EJB
    private CardTypesService cardTypes;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCardTypes() {
        Gson gson = new Gson();
        return gson.toJson(cardTypes.getCardTypes());
    }

    @GET
    @Path("add/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean addCardType(@PathParam("name")String name) {
        return cardTypes.createCardType(name);
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deleteCardType(@PathParam("id")int id) {
        return cardTypes.deleteCardType(cardTypes.getCardTypeById(id));
    }

    @GET
    @Path("change/{id}/name{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changeCardTypeName(@PathParam("id")int id, @PathParam("name")String name) {
        return cardTypes.changeCardTypeName(cardTypes.getCardTypeById(id), name);
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCardTypeByName(@PathParam("name")String name) {
        Gson gson = new Gson();
        return gson.toJson(cardTypes.getCardTypeByName(name));
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCardTypeById(@PathParam("id")int id) {
        Gson gson = new Gson();
        return gson.toJson(cardTypes.getCardTypeById(id));
    }
}
