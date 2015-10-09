package vi.talii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class Player extends GeneratedIdentifierEntity{

    @Column(name = "cash", length = 6)
    private double cash;

    public Player(int account_id, double cash) {
        setId(account_id);
        this.cash = cash;
    }

    public Player() {
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }



}
