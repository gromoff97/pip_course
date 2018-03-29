package course.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class RESTConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(CardsResource.class);
        s.add(CardTypesResource.class);
        s.add(LinesResource.class);
        s.add(LostFoundResource.class);
        s.add(NewsResource.class);
        s.add(PathResource.class);
        s.add(PermissionTypesResource.class);
        s.add(StationsResource.class);
        s.add(StationsStatesResource.class);
        s.add(UsersResource.class);
        return s;
    }
}
