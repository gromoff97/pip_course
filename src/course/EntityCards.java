package course;

import javax.persistence.*;

@Entity(name = "cards")
public class EntityCards {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cards_id_seq")
    @SequenceGenerator(name = "cards_id_seq", sequenceName = "cards_id_seq", allocationSize = 1)
    private int id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "people_id", nullable = false)
    private EntityUsers user;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", nullable = false)
    private EntityCardTypes cardType;

    @Column(nullable = false)
    private int balance;

    public EntityCards() {
    }

    public EntityCards(EntityUsers user, EntityCardTypes cardType) {
        this.user = user;
        this.cardType = cardType;
        this.balance = 0;
    }

    public EntityUsers getUser() {
        return user;
    }

    public EntityCardTypes getCardType() {
        return cardType;
    }

    public int getBalance() {
        return balance;
    }

    public void setCardType(EntityCardTypes cardType) {
        this.cardType = cardType;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
