package vi.talii.model;


public class Card {

    private SuitType suit;
    private RankType rank;

    public Card(SuitType suit, RankType rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card() {
    }

    public SuitType getSuit() {
        return suit;
    }

    public void setSuit(SuitType suit) {
        this.suit = suit;
    }

    public RankType getRank() {
        return rank;
    }

    public void setRank(RankType rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Card{" +
                "Rank='" + rank + '\'' +
                ", suit=" + suit +
                '}';
    }

    public int getValue() {
        if (rank == rank.JACK || rank == rank.QUEEN || rank == rank.KING) {
            return 10;
        }
        return rank.ordinal() + 1;
    }
}
