package course;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "stations")
public class EntityStations {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stations_id_seq")
    @SequenceGenerator(name = "stations_id_seq", sequenceName = "stations_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "line_id", nullable = false)
    private EntityLines line;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id", nullable = false)
    private EntityStationsStates state;

    @OneToMany(mappedBy = "fromStation")
    private Collection<EntityPath> pathFrom;

    @OneToMany(mappedBy = "toStation")
    private Collection<EntityPath> pathTo;

    public EntityStations(String name, EntityLines line, EntityStationsStates state) {
        this.name = name;
        this.line = line;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public EntityLines getLine() {
        return line;
    }

    public EntityStationsStates getState() {
        return state;
    }

    public Collection<EntityPath> getPathFrom() {
        return pathFrom;
    }

    public Collection<EntityPath> getPathTo() {
        return pathTo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(EntityStationsStates state) {
        this.state = state;
    }
}
