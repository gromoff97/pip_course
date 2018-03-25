package course;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Locale;

@Stateless
@Path("test")
public class Test {
    private EntityManagerFactory entityManagerFactory;

    public Test() {
        //Locale.setDefault(Locale.ENGLISH);
        entityManagerFactory = Persistence.createEntityManagerFactory("course.database");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getResult() {
        return "Test";
    }
}
