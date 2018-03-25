package course;

import javax.persistence.*;

@Entity(name = "cards")
public class EntityCards {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cards_id_seq")
    @SequenceGenerator(name = "cards_id_seq", sequenceName = "cards_id_seq", allocationSize = 1)
    private int id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "people_id")
    private EntityUsers users;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private EntityCardTypes cardType;

    @Column(nullable = false)
    private int balance;
}
