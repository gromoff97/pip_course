package course.entity;

import javax.persistence.*;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Entity class describing list of all <strong>states</strong> stations can be in.
 * See also {@link course.service.StationStatesService}.
 **/

@Entity
@Table(name = "stations_states")
public class EntityStationsStates {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stations_states_id_seq")
    @SequenceGenerator(name = "stations_states_id_seq", sequenceName = "stations_states_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    public EntityStationsStates() {
    }

    public EntityStationsStates(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
