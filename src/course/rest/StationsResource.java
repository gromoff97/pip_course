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
    public boolean getAllStations(@PathParam("name")String name, @PathParam("line")int line,@PathParam("state")int state) {
        return stations.createStation(name, lines.getLineById(line), stationStates.getStateById(state));
    }
}
