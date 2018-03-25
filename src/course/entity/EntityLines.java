package course.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "lines")
public class EntityLines {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lines_id_seq")
    @SequenceGenerator(name = "lines_id_seq", sequenceName = "lines_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false, unique = true)
    private int schemeNumber;

    @Column(nullable = false, unique = true)
    private String schemeColor;

    @OneToMany(mappedBy = "line")
    private Collection<EntityStations> stations;

    public EntityLines() {
    }

    public EntityLines(int schemeNumber, String schemeColor) {
        this.schemeNumber = schemeNumber;
        this.schemeColor = schemeColor;
    }

    public int getSchemeNumber() {
        return schemeNumber;
    }

    public String getSchemeColor() {
        return schemeColor;
    }

    public Collection<EntityStations> getStations() {
        return stations;
    }
}
