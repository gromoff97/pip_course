package course.entity;

import javax.persistence.*;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Entity class describing list of all unique <strong>lines</strong> with their unique <strong>color</strong>.
 * See also {@link course.service.LinesService}.
 **/

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
}
