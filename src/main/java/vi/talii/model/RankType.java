package vi.talii.model;

public enum RankType {

    ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    public int getValue() {
        return ordinal() + 1;
    }

}
