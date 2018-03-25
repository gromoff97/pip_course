package course;

import javax.persistence.*;

@Entity
public class JPATest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_id_seq")
    @SequenceGenerator(name = "test_id_seq", sequenceName = "test_id_seq", allocationSize = 1)
    private int id;

    @Column
    private String value;
}
