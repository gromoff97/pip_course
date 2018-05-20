package course.rest;

import com.google.gson.Gson;
import course.entity.EntityUsers;
import course.messages.MessageService;
import course.service.PermissionTypesService;
import course.service.UsersService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Stateless
@Path("users")
public class UsersResource {
    @EJB
    private UsersService users;

    @EJB
    private PermissionTypesService permissionTypes;

    @EJB
    private MessageService messageService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        Gson gson = new Gson();
        return gson.toJson(users.getUsers());
    }

    @GET
    @Path("add/{name}/{lName}/{date}/{eMail}/{type}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean addUser(@PathParam("name")String firstName, @PathParam("lName")String lastName,
                           @PathParam("date")String birthDate, @PathParam("eMail")String eMail, @PathParam("type")int permType) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date;
        try {
            date = new Date(format.parse(birthDate).getTime());
        } catch (Exception e) {
            return false;
        }
        String msg = "User " + lastName + " " + firstName + " was added";
        boolean res = users.createUser(firstName, lastName, date, eMail, permissionTypes.getPermTypeById(permType));
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deleteUser(@PathParam("id")int id) {
        EntityUsers user = users.getUserById(id);
        if (user == null) {
            return false;
        }
        String msg = "User " + user.getLastName() + " " + user.getFirstName() + " was removed";
        boolean res = users.deleteUser(user);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("change/{id}/firstname/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changeUserFirstName(@PathParam("id")int id, @PathParam("name")String name) {
        EntityUsers user = users.getUserById(id);
        if (user == null) {
            return false;
        }
        String msg = "User " + user.getLastName() + " " + user.getFirstName() + " was changed to " +
                user.getLastName() + " " + name;
        boolean res = users.changeUserFirstName(user, name);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("change/{id}/lastname/{lName}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changeUserLastName(@PathParam("id")int id, @PathParam("lName")String lName) {
        EntityUsers user = users.getUserById(id);
        if (user == null) {
            return false;
        }
        String msg = "User " + user.getLastName() + " " + user.getFirstName() + " was changed to " +
                lName + " " + user.getFirstName();
        boolean res = users.changeUserLastName(user, lName);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("change/{id}/balance/{balance}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean changeUserBalance(@PathParam("id")int id, @PathParam("balance")int balance) {
        EntityUsers user = users.getUserById(id);
        if (user == null) {
            return false;
        }
        String msg = "User " + user.getLastName() + " " + user.getFirstName() + "'s balance was changed from " +
                user.getBalance() + " to " + balance;
        boolean res = users.changeUserBalance(user, balance);
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("email/{eMail}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserByEmail(@PathParam("eMail")String eMail) {
        Gson gson = new Gson();
        return gson.toJson(users.getUserByEmail(eMail));
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserById(@PathParam("id")int id) {
        Gson gson = new Gson();
        return gson.toJson(users.getUserById(id));
    }

    @GET
    @Path("balance/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getBalanceById(@PathParam("id")int id) {
        try {
            return Response.ok(users.getBalanceById(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
