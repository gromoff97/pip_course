package course;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "stations")
public class EntityStations {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stations_id_seq")
    @SequenceGenerator(name = "stations_id_seq", sequenceName = "stations_id_seq", allocationSize = 1)
    private int id;

    @Column
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "line_id")
    private EntityLines line;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id")
    private EntityStationsStates state;

    @OneToMany(mappedBy = "fromStation")
    private Collection<EntityPath> pathFrom;

    @OneToMany(mappedBy = "toStation")
    private Collection<EntityPath> pathTo;
}
