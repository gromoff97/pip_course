package course.entity;


import javax.persistence.*;
import java.sql.Date;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Entity class showing list of all news is related to metro
 * Contains <strong>message</strong> and <strong>Date</strong> for each record (as {@link EntityLostFound} do).
 * See also {@link course.service.NewsService}.
 **/

@Entity
@Table(name = "news")
public class EntityNews {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_id_seq")
    @SequenceGenerator(name = "news_id_seq", sequenceName = "news_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date date;

    public EntityNews() {
    }

    public EntityNews(String content, Date date) {
        this.content = content;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
