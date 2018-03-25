package course;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class EntityPath {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "path_id_seq")
    @SequenceGenerator(name = "path_id_seq", sequenceName = "path_id_seq", allocationSize = 1)
    private int id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "from_station")
    private EntityStations fromStation;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "to_station")
    private EntityStations toStation;

    @Column(nullable = false)
    private Time time;
}
