package vi.talii.service;

import vi.talii.model.Card;
import vi.talii.model.SuitType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vitalii on 06.10.2015.
 */
public class SetServiceImpl {

    private static List<Card> defaultSet = new ArrayList<Card>();



    public SetServiceImpl() {
    }

    public static List<Card> shuffle() {
        List<Card> unShuffledSet = new ArrayList<Card>(defaultSet);
        //Collections.copy(unShuffledSet, defaultSet);
        List<Card> shuffledSet = new ArrayList<Card>();
        int i = 0;
        while(unShuffledSet.size() > 0){
            int randCard = (int)(Math.random()*(52 - i));
            shuffledSet.add(unShuffledSet.get(randCard));
            unShuffledSet.remove(randCard);
            i++;
        }
        System.out.println(shuffledSet);
        return shuffledSet;
    }

    public static Card getTopCard(List<Card> set) {
        Card topCard = set.get(0);
        set.remove(0);
        return topCard;
    }

    static {
        defaultSet.add(new Card(SuitType.HEART, "2", 2));
        defaultSet.add(new Card(SuitType.HEART, "3", 3));
        defaultSet.add(new Card(SuitType.HEART, "4", 4));
        defaultSet.add(new Card(SuitType.HEART, "5", 5));
        defaultSet.add(new Card(SuitType.HEART, "6", 6));
        defaultSet.add(new Card(SuitType.HEART, "7", 7));
        defaultSet.add(new Card(SuitType.HEART, "8", 8));
        defaultSet.add(new Card(SuitType.HEART, "9", 9));
        defaultSet.add(new Card(SuitType.HEART, "10", 10));
        defaultSet.add(new Card(SuitType.HEART, "Jack", 10));
        defaultSet.add(new Card(SuitType.HEART, "Queen", 10));
        defaultSet.add(new Card(SuitType.HEART, "King", 10));
        defaultSet.add(new Card(SuitType.HEART, "Ace", 11));
        defaultSet.add(new Card(SuitType.DIAMOND, "2", 2));
        defaultSet.add(new Card(SuitType.DIAMOND, "3", 3));
        defaultSet.add(new Card(SuitType.DIAMOND, "4", 4));
        defaultSet.add(new Card(SuitType.DIAMOND, "5", 5));
        defaultSet.add(new Card(SuitType.DIAMOND, "6", 6));
        defaultSet.add(new Card(SuitType.DIAMOND, "7", 7));
        defaultSet.add(new Card(SuitType.DIAMOND, "8", 8));
        defaultSet.add(new Card(SuitType.DIAMOND, "9", 9));
        defaultSet.add(new Card(SuitType.DIAMOND, "10", 10));
        defaultSet.add(new Card(SuitType.DIAMOND, "Jack", 10));
        defaultSet.add(new Card(SuitType.DIAMOND, "Queen", 10));
        defaultSet.add(new Card(SuitType.DIAMOND, "King", 10));
        defaultSet.add(new Card(SuitType.DIAMOND, "Ace", 11));
        defaultSet.add(new Card(SuitType.CLUB, "2", 2));
        defaultSet.add(new Card(SuitType.CLUB, "3", 3));
        defaultSet.add(new Card(SuitType.CLUB, "4", 4));
        defaultSet.add(new Card(SuitType.CLUB, "5", 5));
        defaultSet.add(new Card(SuitType.CLUB, "6", 6));
        defaultSet.add(new Card(SuitType.CLUB, "7", 7));
        defaultSet.add(new Card(SuitType.CLUB, "8", 8));
        defaultSet.add(new Card(SuitType.CLUB, "9", 9));
        defaultSet.add(new Card(SuitType.CLUB, "10", 10));
        defaultSet.add(new Card(SuitType.CLUB, "Jack", 10));
        defaultSet.add(new Card(SuitType.CLUB, "Queen", 10));
        defaultSet.add(new Card(SuitType.CLUB, "King", 10));
        defaultSet.add(new Card(SuitType.CLUB, "Ace", 11));
        defaultSet.add(new Card(SuitType.SPADE, "2", 2));
        defaultSet.add(new Card(SuitType.SPADE, "3", 3));
        defaultSet.add(new Card(SuitType.SPADE, "4", 4));
        defaultSet.add(new Card(SuitType.SPADE, "5", 5));
        defaultSet.add(new Card(SuitType.SPADE, "6", 6));
        defaultSet.add(new Card(SuitType.SPADE, "7", 7));
        defaultSet.add(new Card(SuitType.SPADE, "8", 8));
        defaultSet.add(new Card(SuitType.SPADE, "9", 9));
        defaultSet.add(new Card(SuitType.SPADE, "10", 10));
        defaultSet.add(new Card(SuitType.SPADE, "Jack", 10));
        defaultSet.add(new Card(SuitType.SPADE, "Queen", 10));
        defaultSet.add(new Card(SuitType.SPADE, "King", 10));
        defaultSet.add(new Card(SuitType.SPADE, "Ace", 11));

    }
}
