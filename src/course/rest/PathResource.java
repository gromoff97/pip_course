package course.rest;

import com.google.gson.Gson;
import course.service.PathService;
import course.service.StationsService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("path")
public class PathResource {
    @EJB
    private PathService path;

    @EJB
    private StationsService stations;

    @GET
    @Path("add/{from}/{to}/{time}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean addPath(@PathParam("from")int from, @PathParam("to")int to, @PathParam("time")int time) {
        return path.createPath(stations.getStationById(from), stations.getStationById(to), time);
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deletePath(@PathParam("id")int id) {
        return path.deletePath(path.getPathById(id));
    }

    @GET
    @Path("change/{id}/time/{time}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changePathTime(@PathParam("id")int id, @PathParam("time")int time) {
        return path.changePathTime(path.getPathById(id), time);
    }

    @GET
    @Path("from/{from}/to/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPathFromTo(@PathParam("from")int from, @PathParam("to")int to) {
        Gson gson = new Gson();
        return gson.toJson(path.getPathFromTo(stations.getStationById(from), stations.getStationById(to)));
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPathById(@PathParam("id")int id) {
        Gson gson = new Gson();
        return gson.toJson(path.getPathById(id));
    }
}
