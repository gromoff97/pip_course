package course;

import javax.persistence.*;

@Entity(name = "permission_types")
public class EntityPermissionTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_types_id_seq")
    @SequenceGenerator(name = "permission_types_id_seq", sequenceName = "permission_types_id_seq", allocationSize = 1)
    private int id;

    @Column
    private String name;
}
