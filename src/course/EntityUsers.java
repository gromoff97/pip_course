package course;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity(name = "users")
public class EntityUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private Date birthDate;

    @Column(nullable = false)
    private String eMail;

    @Column(nullable = false)
    private int balance;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "permission_type_id", nullable = false)
    private EntityPermissionTypes userPermissionType;

    @OneToMany(mappedBy = "users")
    private Collection<EntityCards> cards;
}
