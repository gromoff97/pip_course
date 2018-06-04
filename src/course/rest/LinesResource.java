package course.rest;

import course.entity.EntityLines;
import course.messages.MessageService;
import course.service.LinesService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Stateless
@Path("lines")
public class LinesResource {
    @EJB
    private LinesService lines;

    @EJB
    private MessageService messageService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<EntityLines> getAllLines() {
        return lines.getLines();
    }

    @GET
    @Path("add/{number}/{color}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addLine(@PathParam("number")int num, @PathParam("color")String color) {
        String msg = color + " line was added";
        boolean res = lines.createLine(num, color);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addLine(@PathParam("id")int id) {
        EntityLines line = lines.getLineById(id);
        if (line == null) {
            return false;
        }
        String msg = line.getSchemeColor() + " line was removed";
        boolean res = lines.deleteLine(line);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("color/{color}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityLines getLineByColor(@PathParam("color")String color) {
        return lines.getLineByColor(color);
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityLines getLineById(@PathParam("id")int id) {
        return lines.getLineById(id);
    }
}
