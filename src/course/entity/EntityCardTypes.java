package course.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Entity class describing list of all <strong>types</strong> cards can have.
 * See also {@link course.service.CardTypesService}.
 **/

@Entity
@Table(name = "card_types")
public class EntityCardTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_types_id_seq")
    @SequenceGenerator(name = "card_types_id_seq", sequenceName = "card_types_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "cardType")
    private Collection<EntityCards> cards;

    public EntityCardTypes() {
    }

    public EntityCardTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Collection<EntityCards> getCards() {
        return cards;
    }

    public void setName(String name) {
        this.name = name;
    }
}
