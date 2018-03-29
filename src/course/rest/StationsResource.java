package course.rest;

import com.google.gson.Gson;
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
        return stations.createStation(name, lines.getLineById(line), stationStates.getStateById(state));
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deleteStation(@PathParam("id")int id) {
        return stations.deleteStation(stations.getStationById(id));
    }

    @GET
    @Path("change/{id}/name/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changeStationName(@PathParam("id")int id, @PathParam("name")String name) {
        return stations.changeStationName(stations.getStationById(id), name);
    }

    @GET
    @Path("change/{id}/state/{state}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changeStationState(@PathParam("id")int id, @PathParam("state")int state) {
        return stations.changeStationState(stations.getStationById(id), stationStates.getStateById(state));
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
