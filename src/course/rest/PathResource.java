package course.rest;

import com.google.gson.Gson;
import course.entity.EntityPath;
import course.entity.EntityStations;
import course.messages.MessageService;
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

    @EJB
    private MessageService messageService;

    @GET
    @Path("add/{from}/{to}/{time}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean addPath(@PathParam("from")int from, @PathParam("to")int to, @PathParam("time")int time) {
        EntityStations fromStation = stations.getStationById(from),
                toStation = stations.getStationById(to);
        String msg = "Path from station " + fromStation.getName() + " to station " + toStation.getName() +
                " was added";
        boolean res = path.createPath(fromStation, toStation, time);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deletePath(@PathParam("id")int id) {
        EntityPath oldPath = path.getPathById(id);
        String msg = "Path from station" + oldPath.getFromStation().getName() + " to station " +
                oldPath.getToStation().getName() + " was removed";
        boolean res = path.deletePath(oldPath);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("change/{id}/time/{time}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changePathTime(@PathParam("id")int id, @PathParam("time")int time) {
        EntityPath ePath = path.getPathById(id);
        String msg = "New time from station " + ePath.getFromStation().getName() + " to station " +
                ePath.getToStation().getName() + " is " + time + " seconds";
        boolean res = path.changePathTime(ePath, time);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
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
