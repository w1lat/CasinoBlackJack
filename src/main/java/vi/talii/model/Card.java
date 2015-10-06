package vi.talii.model;


public class Card {

    private SuitType suit;
    private String Index;
    private int Count;

    public Card(SuitType suit, String index, int count) {
        this.suit = suit;
        Index = index;
        Count = count;
    }

    public Card() {
    }

    public SuitType getSuit() {
        return suit;
    }

    public void setSuit(SuitType suit) {
        this.suit = suit;
    }

    public String getIndex() {
        return Index;
    }

    public void setIndex(String index) {
        Index = index;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    @Override
    public String toString() {
        return "Card{" +
                "Index='" + Index + '\'' +
                ", suit=" + suit +
                '}';
    }
}
