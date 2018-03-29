package course.rest;

import com.google.gson.Gson;
import course.service.LinesService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("lines")
public class LinesResource {
    @EJB
    private LinesService lines;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllLines() {
        Gson gson = new Gson();
        return gson.toJson(lines.getLines());
    }

    @GET
    @Path("add/{number}/{color}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean addLine(@PathParam("number")int num, @PathParam("color")String color) {
        return lines.createLine(num, color);
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean addLine(@PathParam("id")int id) {
        return lines.deleteLine(lines.getLineById(id));
    }

    @GET
    @Path("color/{color}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLineByColor(@PathParam("color")String color) {
        Gson gson = new Gson();
        return gson.toJson(lines.getLineByColor(color));
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLineById(@PathParam("id")int id) {
        Gson gson = new Gson();
        return gson.toJson(lines.getLineById(id));
    }
}
