package vi.talii.model.to;

import vi.talii.model.TransactionType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long playerId;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TransactionType paymentType;

    @Column
    private double amount;

    public Transaction() {
    }

    public Transaction(Long playerId, TransactionType paymentType, double amount) {
        this.playerId = playerId;
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public TransactionType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(TransactionType paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", paymentType=" + paymentType +
                ", amount=" + amount +
                '}';
    }
}
