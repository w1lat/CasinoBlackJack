package vi.talii.model;


public class Player {

    private int account_id;
    private int cash;
    private int bet;
    private int win;

    public Player(int account_id, int cash, int bet, int win) {
        this.account_id = account_id;
        this.cash = cash;
        this.bet = bet;
        this.win = win;
    }

    public Player() {
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }


}
