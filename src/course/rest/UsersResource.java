package course.rest;

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
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

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
    public Collection<EntityUsers> getAllUsers() {
        return users.getUsers();
    }

    @GET
    @Path("add/{login}/{name}/{lName}/{date}/{eMail}/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addUser(@PathParam("login")String login, @PathParam("name")String firstName, @PathParam("lName")String lastName,
                           @PathParam("date")String birthDate, @PathParam("eMail")String eMail, @PathParam("type")int permType) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date;
        try {
            date = new Date(format.parse(birthDate).getTime());
        } catch (Exception e) {
            return false;
        }
        String msg = "User " + lastName + " " + firstName + " was added";
        boolean res = users.createUser(login, firstName, lastName, date, eMail, permissionTypes.getPermTypeById(permType));
        if (res) {
            messageService.sendMsg(msg);
        }
        return res;
    }

    @GET
    @Path("rm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
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
    public EntityUsers getUserByEmail(@PathParam("eMail")String eMail) {
        return users.getUserByEmail(eMail);
    }

    @GET
    @Path("login/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityUsers getUserByLogin(@PathParam("login")String login) {
        return users.getUserByLogin(login);
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityUsers getUserById(@PathParam("id")int id) {
        return users.getUserById(id);
    }

    @GET
    @Path("balance/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getBalanceById(@PathParam("id")int id) {
        try {
            return users.getBalanceById(id);
        } catch (Exception e) {
            return null;
        }
    }
}
