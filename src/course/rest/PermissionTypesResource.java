package course.rest;

import com.google.gson.Gson;
import course.entity.EntityPermissionTypes;
import course.messages.MessageService;
import course.service.PermissionTypesService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("permtypes")
public class PermissionTypesResource {
    @EJB
    private PermissionTypesService permissionTypes;

    @EJB
    private MessageService messageService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPermTypes() {
        Gson gson = new Gson();
        return gson.toJson(permissionTypes.getPermTypes());
    }

    @GET
    @Path("add/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean addPermType(@PathParam("name")String name) {
        String msg = "Permission type " + name + " was added";
        boolean res = permissionTypes.createType(name);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deletePermType(@PathParam("id")int id) {
        EntityPermissionTypes pType = permissionTypes.getPermTypeById(id);
        if (pType == null) {
            return false;
        }
        String msg = "Permission type " + pType.getName() + " was removed";
        boolean res = permissionTypes.deleteType(pType);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("change/{id}/name/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changePermissionName(@PathParam("id")int id, @PathParam("name")String name) {
        EntityPermissionTypes pType = permissionTypes.getPermTypeById(id);
        if (pType == null) {
            return false;
        }
        String msg = "Permission type " + pType.getName() + " was changed to " + name;
        boolean res = permissionTypes.changePermissionName(pType, name);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPermTypeByName(@PathParam("name")String name) {
        Gson gson = new Gson();
        return gson.toJson(permissionTypes.getPermTypeByName(name));
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPermTypeById(@PathParam("id")int id) {
        Gson gson = new Gson();
        return gson.toJson(permissionTypes.getPermTypeById(id));
    }
}
