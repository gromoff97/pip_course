package course;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "lost_found")
public class EntityLostFound {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lost_found_id_seq")
    @SequenceGenerator(name = "lost_found_id_seq", sequenceName = "lost_found_id_seq", allocationSize = 1)
    private int id;

    @Column
    private String message;

    @Column
    private Date date;

}
