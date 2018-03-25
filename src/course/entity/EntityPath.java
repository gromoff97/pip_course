package course.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity(name = "path")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"from_station", "to_station"})})
public class EntityPath {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "path_id_seq")
    @SequenceGenerator(name = "path_id_seq", sequenceName = "path_id_seq", allocationSize = 1)
    private int id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "from_station", nullable = false)
    private EntityStations fromStation;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "to_station", nullable = false)
    private EntityStations toStation;

    @Column(nullable = false)
    private Time time;

    public EntityPath() {
    }

    public EntityPath(EntityStations fromStation, EntityStations toStation, Time time) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.time = time;
    }

    public EntityStations getFromStation() {
        return fromStation;
    }

    public EntityStations getToStation() {
        return toStation;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
