package course;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "permission_types")
public class EntityPermissionTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_types_id_seq")
    @SequenceGenerator(name = "permission_types_id_seq", sequenceName = "permission_types_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "userPermissionType")
    private Collection<EntityUsers> users;
}
