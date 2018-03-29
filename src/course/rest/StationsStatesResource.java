package course.rest;

import com.google.gson.Gson;
import course.service.StationStatesService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("states")
public class StationsStatesResource {
    @EJB
    private StationStatesService stationStates;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllStates() {
        Gson gson = new Gson();
        return gson.toJson(stationStates.getStates());
    }

    @GET
    @Path("add/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean addState(@PathParam("name")String name) {
        return stationStates.createState(name);
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deleteState(@PathParam("id")int id) {
        return stationStates.deleteState(stationStates.getStateById(id));
    }

    @GET
    @Path("change/{id}/name/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changeStateName(@PathParam("id")int id, @PathParam("name")String name) {
        return stationStates.changeStateName(stationStates.getStateById(id), name);
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStateByName(@PathParam("name")String name) {
        Gson gson = new Gson();
        return gson.toJson(stationStates.getStateByName(name));
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStateById(@PathParam("id")int id) {
        Gson gson = new Gson();
        return gson.toJson(stationStates.getStateById(id));
    }
}
