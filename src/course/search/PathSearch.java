package course.search;

import course.entity.EntityPath;
import course.entity.EntityStations;
import course.service.PathService;
import course.service.StationsService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class PathSearch {
    @EJB
    private StationsService stationsService;

    @EJB
    private PathService pathService;

    public FoundPath getMinPath(EntityStations from, EntityStations to) throws Exception {
        if ((from == null) || (to == null)) {
            throw new Exception();
        }
        Collection<EntityStations> collection = stationsService.getStations();
        HashMap<EntityStations, Integer> stations = new HashMap<>(collection.size());
        for (EntityStations station : collection) {
            stations.put(station, stations.size());
        }

        Graph graph = new Graph(stations.size());

        Collection<EntityPath> paths = pathService.getPaths();
        for (EntityPath path : paths) {
            int v = stations.get(path.getFromStation()),
                    u = stations.get(path.getToStation()),
                    time = path.getTimeInSec();
            graph.add(v, u, time);
            graph.add(u, v, time);
        }

        int v = stations.get(from),
                u = stations.get(to);

        int time = graph.getMinDistance(v, u);
        List<Integer> path = graph.getLastPath(v, u);
        StringBuilder result = new StringBuilder();
        for (Integer item : path) {
            result.append(getStationFromMap(stations, item).getName()).append(" -> ");
        }
        result.delete(result.length() - 4, result.length());
        return new FoundPath(result.toString(), time);
    }

    private EntityStations getStationFromMap(HashMap<EntityStations, Integer> map, int val) {
        for (Map.Entry<EntityStations, Integer> entry : map.entrySet()) {
            if (entry.getValue() == val) {
                return entry.getKey();
            }
        }
        return null;
    }
}
