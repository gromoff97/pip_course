package course.entity;

import javax.persistence.*;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Entity class describing list of paths can user move.
 * Contains <strong>start station</strong> (see {@link EntityStations}),
 * <strong>end station</strong> (see {@link EntityStations})
 * and <strong>destination time</strong> for each record.
 * See also {@link course.service.PathService}.
 **/

@Entity
@Table(name = "path", uniqueConstraints = {@UniqueConstraint(columnNames = {"from_station", "to_station"})})
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
    private int timeInSec;

    public EntityPath() {
    }

    public EntityPath(EntityStations fromStation, EntityStations toStation, int timeInSec) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.timeInSec = timeInSec;
    }

    public EntityStations getFromStation() {
        return fromStation;
    }

    public EntityStations getToStation() {
        return toStation;
    }

    public int getTimeInSec() {
        return timeInSec;
    }

    public void setTimeInSec(int timeInSec) {
        this.timeInSec = timeInSec;
    }
}
