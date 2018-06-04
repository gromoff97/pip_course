package course.entity;

import javax.persistence.*;

/**
 * @author Gromov Anton
 * @author Yriy Tkachev
 *
 * @version 0.9
 *
 * Entity class describing list of all <strong>permissions</strong> user can have.
 * See also {@link course.service.PermissionTypesService}.
 **/

@Entity
@Table(name = "permission_types")
public class EntityPermissionTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_types_id_seq")
    @SequenceGenerator(name = "permission_types_id_seq", sequenceName = "permission_types_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    public EntityPermissionTypes() {
    }

    public EntityPermissionTypes(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
