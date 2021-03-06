package vi.talii.model.to;

import vi.talii.model.GeneratedIdentifierEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Entity
@Table(name = "players")
@XmlRootElement
public class Player extends GeneratedIdentifierEntity {

    @Column(name = "cash", length = 6)
    private double balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playerId")
    private Set<Transaction> moneyTransactions;

    public Player(int account_id, double balance) {
        setId(account_id);
        this.balance = balance;
    }

    public Player() {
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double cash) {
        this.balance = cash;
    }

    public Set<Transaction> getMoneyTransactions() {
        return moneyTransactions;
    }

    public void setMoneyTransactions(Set<Transaction> moneyTransactions) {
        this.moneyTransactions = moneyTransactions;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + getId() + " " +
                "balance=" + balance +
                '}';
    }
}
