package course.rest;

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
import java.util.Collection;

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
    public Collection<EntityPermissionTypes> getAllPermTypes() {
        return permissionTypes.getPermTypes();
    }

    @GET
    @Path("add/{name}")
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
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
    public EntityPermissionTypes getPermTypeByName(@PathParam("name")String name) {
        return permissionTypes.getPermTypeByName(name);
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityPermissionTypes getPermTypeById(@PathParam("id")int id) {
        return permissionTypes.getPermTypeById(id);
    }
}
