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

    @Column(nullable = false, unique = true)
    private String eMail;

    @Column(nullable = false)
    private int balance;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "permission_type_id", nullable = false)
    private EntityPermissionTypes userPermissionType;

    @OneToMany(mappedBy = "user")
    private Collection<EntityCards> cards;

    public EntityUsers(String firstName, String lastName, Date birthDate, String eMail, EntityPermissionTypes userPermissionType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.eMail = eMail;
        this.balance = 20;
        this.userPermissionType = userPermissionType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String geteMail() {
        return eMail;
    }

    public int getBalance() {
        return balance;
    }

    public EntityPermissionTypes getUserPermissionType() {
        return userPermissionType;
    }

    public Collection<EntityCards> getCards() {
        return cards;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setUserPermissionType(EntityPermissionTypes userPermissionType) {
        this.userPermissionType = userPermissionType;
    }
}
