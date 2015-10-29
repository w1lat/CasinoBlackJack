package vi.talii.model;

public enum RankType {

    ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    // returns points for a card depends on it's rank from 1 to 10
    public int getValue() {
        return ordinal() + 1;
    }

}
