package vi.talii.model;


public class Player {

    private int account_id;
    private float cash;

    public Player(int account_id, float cash) {
        this.account_id = account_id;
        this.cash = cash;
    }

    public Player() {
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }



}
