package course.rest;

import course.entity.EntityStationsStates;
import course.messages.MessageService;
import course.service.StationStatesService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Stateless
@Path("states")
public class StationsStatesResource {
    @EJB
    private StationStatesService stationStates;

    @EJB
    private MessageService messageService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<EntityStationsStates> getAllStates() {
        return stationStates.getStates();
    }

    @GET
    @Path("add/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addState(@PathParam("name")String name) {
        String msg = "Station's state " + name + " was added";
        boolean res = stationStates.createState(name);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteState(@PathParam("id")int id) {
        EntityStationsStates state = stationStates.getStateById(id);
        if (state == null) {
            return false;
        }
        String msg = "Station's state " + state.getName() + " was removed";
        boolean res = stationStates.deleteState(state);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("change/{id}/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean changeStateName(@PathParam("id")int id, @PathParam("name")String name) {
        EntityStationsStates state = stationStates.getStateById(id);
        if (state == null) {
            return false;
        }
        String msg = "Station's state " + state.getName() + " was changed to " + name;
        boolean res = stationStates.changeStateName(state, name);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityStationsStates getStateByName(@PathParam("name")String name) {
        return stationStates.getStateByName(name);
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityStationsStates getStateById(@PathParam("id")int id) {
        return stationStates.getStateById(id);
    }
}
