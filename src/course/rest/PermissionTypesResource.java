package course.rest;

import com.google.gson.Gson;
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
        return permissionTypes.createType(name);
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deletePermType(@PathParam("id")int id) {
        return permissionTypes.deleteType(permissionTypes.getPermTypeById(id));
    }

    @GET
    @Path("change/{id}/name/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changePermissionName(@PathParam("id")int id, @PathParam("name")String name) {
        return permissionTypes.changePermissionName(permissionTypes.getPermTypeById(id), name);
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
