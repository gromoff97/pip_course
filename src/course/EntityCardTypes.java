package course;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "card_types")
public class EntityCardTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_types_id_seq")
    @SequenceGenerator(name = "card_types_id_seq", sequenceName = "card_types_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "cardType")
    private Collection<EntityCards> cards;
}
