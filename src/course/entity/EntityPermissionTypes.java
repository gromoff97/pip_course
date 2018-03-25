package course.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "permission_types")
public class EntityPermissionTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_types_id_seq")
    @SequenceGenerator(name = "permission_types_id_seq", sequenceName = "permission_types_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "userPermissionType")
    private Collection<EntityUsers> users;

    public EntityPermissionTypes() {
    }

    public EntityPermissionTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Collection<EntityUsers> getUsers() {
        return users;
    }

    public void setName(String name) {
        this.name = name;
    }
}
