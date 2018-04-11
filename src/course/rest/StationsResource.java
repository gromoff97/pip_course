package course.rest;

import com.google.gson.Gson;
import course.messages.MessageService;
import course.service.LinesService;
import course.service.StationStatesService;
import course.service.StationsService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("stations")
public class StationsResource {
    @EJB
    private StationsService stations;

    @EJB
    private LinesService lines;

    @EJB
    private StationStatesService stationStates;

    @EJB
    private MessageService messageService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllStations() {
        Gson gson = new Gson();
        return gson.toJson(stations.getStations());
    }

    @GET
    @Path("add/{name}/{line}/{state}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean addStation(@PathParam("name")String name, @PathParam("line")int line,@PathParam("state")int state) {
        String msg = "Station " + name + " was added";
        boolean result = stations.createStation(name, lines.getLineById(line), stationStates.getStateById(state));
        if (result) {
            messageService.sendMsg(msg);
        }
        return result;
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deleteStation(@PathParam("id")int id) {
        String msg = "Station " + stations.getStationById(id).getName() + " was removed";
        boolean result = stations.deleteStation(stations.getStationById(id));
        if (result) {
            messageService.sendMsg(msg);
        }
        return result;
    }

    @GET
    @Path("change/{id}/name/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changeStationName(@PathParam("id")int id, @PathParam("name")String name) {
        String msg = "Station " + stations.getStationById(id).getName() + "'s name was changed to " + name;
        boolean result = stations.changeStationName(stations.getStationById(id), name);
        if (result) {
            messageService.sendMsg(msg);
        }
        return result;
    }

    @GET
    @Path("change/{id}/state/{state}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changeStationState(@PathParam("id")int id, @PathParam("state")int state) {
        String msg = "Station " + stations.getStationById(id).getName() + "'s state was changed to " +
                stationStates.getStateById(state).getName();
        boolean result = stations.changeStationState(stations.getStationById(id), stationStates.getStateById(state));
        if (result) {
            messageService.sendMsg(msg);
        }
        return result;
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStationByName(@PathParam("name")String name) {
        Gson gson = new Gson();
        return gson.toJson(stations.getStationByName(name));
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStationById(@PathParam("id")int id) {
        Gson gson = new Gson();
        return gson.toJson(stations.getStationById(id));
    }
}
