package course;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "stations_states")
public class EntityStationsStates {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stations_states_id_seq")
    @SequenceGenerator(name = "stations_states_id_seq", sequenceName = "stations_states_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "state")
    private Collection<EntityStations> stations;

    public EntityStationsStates() {
    }

    public EntityStationsStates(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Collection<EntityStations> getStations() {
        return stations;
    }

    public void setName(String name) {
        this.name = name;
    }
}
