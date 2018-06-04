package course.entity;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Entity class showing list of all stuff dissappeared in metro;
 * Contains <strong>message</strong> and <strong>Date</strong> for each record.
 * See also {@link course.service.LostFoundService}.
 **/

@Entity
@Table(name = "lost_found")
public class EntityLostFound {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lost_found_id_seq")
    @SequenceGenerator(name = "lost_found_id_seq", sequenceName = "lost_found_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Date date;

    public EntityLostFound() {
    }

    public EntityLostFound(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getPubDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(date);
    }
}
