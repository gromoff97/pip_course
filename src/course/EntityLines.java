package course;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "lines")
public class EntityLines {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lines_id_seq")
    @SequenceGenerator(name = "lines_id_seq", sequenceName = "lines_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false)
    private int schemeNumber;

    @Column(nullable = false)
    private String schemeColor;

    @OneToMany(mappedBy = "line")
    private Collection<EntityStations> stations;
}
