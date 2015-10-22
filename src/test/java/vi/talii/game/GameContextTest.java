package vi.talii.game;

import org.junit.Test;
import vi.talii.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class GameContextTest {

    private GameContext gameContext;

    @Test
    public void testPlayerHasBust(){
        List<Card> playerCards = new ArrayList<Card>();
        playerCards.add(new Card(SuitType.SPADE, RankType.KING));
        playerCards.add(new Card(SuitType.DIAMOND, RankType.JACK));
        playerCards.add(new Card(SuitType.HEART, RankType.THREE));

        List<Card> dealerCards = new ArrayList<Card>();
        dealerCards.add(new Card(SuitType.CLUB, RankType.QUEEN));

        gameContext = new GameContext(100, playerCards, dealerCards, null, 1);
        assertTrue("Player loose", gameContext.getGameResult() == GameResult.LOOSE);
    }

    @Test
    public void testDealerHasBust(){
        List<Card> dealerCards = new ArrayList<Card>();
        dealerCards.add(new Card(SuitType.SPADE, RankType.KING));
        dealerCards.add(new Card(SuitType.DIAMOND, RankType.JACK));
        dealerCards.add(new Card(SuitType.HEART, RankType.THREE));

        List<Card> playerCards = new ArrayList<Card>();
        playerCards.add(new Card(SuitType.CLUB, RankType.QUEEN));

        gameContext = new GameContext(100, playerCards, dealerCards, null, 1);
        assertTrue("Dealer loose", gameContext.getGameResult() == GameResult.WIN);
    }

    @Test
    public void testPlayerHas21(){
        List<Card> playerCards = new ArrayList<Card>();
        playerCards.add(new Card(SuitType.SPADE, RankType.KING));
        playerCards.add(new Card(SuitType.DIAMOND, RankType.NINE));
        playerCards.add(new Card(SuitType.HEART, RankType.DEUCE));

        List<Card> dealerCards = new ArrayList<Card>();
        dealerCards.add(new Card(SuitType.CLUB, RankType.QUEEN));

        gameContext = new GameContext(100, playerCards, dealerCards, null, 1);
        assertTrue("Player loose", gameContext.getGameResult() == GameResult.WIN);
    }

    @Test
    public void testDealerHas21(){
        List<Card> dealerCards = new ArrayList<Card>();
        dealerCards.add(new Card(SuitType.SPADE, RankType.KING));
        dealerCards.add(new Card(SuitType.DIAMOND, RankType.EIGHT));
        dealerCards.add(new Card(SuitType.HEART, RankType.THREE));

        List<Card> playerCards = new ArrayList<Card>();
        playerCards.add(new Card(SuitType.CLUB, RankType.QUEEN));

        gameContext = new GameContext(100, playerCards, dealerCards, null, 1);
        assertTrue("Dealer loose", gameContext.getGameResult() == GameResult.LOOSE);
    }

    @Test
    public void testPush(){
        List<Card> dealerCards = new ArrayList<Card>();
        dealerCards.add(new Card(SuitType.SPADE, RankType.KING));
        dealerCards.add(new Card(SuitType.DIAMOND, RankType.EIGHT));

        List<Card> playerCards = new ArrayList<Card>();
        playerCards.add(new Card(SuitType.CLUB, RankType.QUEEN));
        playerCards.add(new Card(SuitType.HEART, RankType.FIVE));
        playerCards.add(new Card(SuitType.DIAMOND, RankType.THREE));

        gameContext = new GameContext(100, playerCards, dealerCards, null, 1);
        assertTrue("Dealer loose", gameContext.getGameResult() == GameResult.PUSH);
    }

    @Test
    public void testBlackJackPush(){
        List<Card> dealerCards = new ArrayList<Card>();
        dealerCards.add(new Card(SuitType.SPADE, RankType.KING));
        dealerCards.add(new Card(SuitType.DIAMOND, RankType.ACE));

        List<Card> playerCards = new ArrayList<Card>();
        playerCards.add(new Card(SuitType.SPADE, RankType.ACE));
        playerCards.add(new Card(SuitType.CLUB, RankType.QUEEN));

        gameContext = new GameContext(100, playerCards, dealerCards, null, 1);
        assertTrue("Dealer loose", gameContext.getGameResult() == GameResult.PUSH);
    }
}
