package course;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "news")
public class EntityNews {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_id_seq")
    @SequenceGenerator(name = "news_id_seq", sequenceName = "news_id_seq", allocationSize = 1)
    private int id;

    @Column
    private String content;

    @Column
    private Date date;
}
