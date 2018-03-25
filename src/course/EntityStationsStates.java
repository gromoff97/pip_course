package course;

import javax.persistence.*;

@Entity(name = "stations_states")
public class EntityStationsStates {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stations_states_id_seq")
    @SequenceGenerator(name = "stations_states_id_seq", sequenceName = "stations_states_id_seq", allocationSize = 1)
    private int id;

    @Column
    private String name;
}
