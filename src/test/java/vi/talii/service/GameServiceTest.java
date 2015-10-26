package vi.talii.service;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import vi.talii.exception.GameContextNotFoundException;
import vi.talii.exception.GameCouldNotBeStartedException;
import vi.talii.exception.GameException;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.*;
import vi.talii.model.to.GameResponse;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;
import static org.junit.Assert.*;

// todo use logger
// TODO add to name word Integration
// todo throws concrete exception, NOT GENERAL
public class GameServiceTest extends InitAppContextBase {


//    можна якось так? (найшов в неті) TODO
//    @Autowired
//    private GameManager gameManager;

//    @Autowired
//    private DeckService deckService;

    public GameServiceTest() {
    }

    @Test
    @Transactional
    public void dealTest() throws GameException {

        GameResponse gameResponse = gameManager.deal(1, 100);
        assertNotNull(gameResponse);
        assertEquals(100, gameResponse.getBet());
        assertNotSame(gameResponse.getPlayerCards(), gameResponse.getDealersCards());
        assertTrue(gameManager.getActiveGames().containsKey(gameResponse.getId()));
    }

    @Test(expected = NoSuchPlayerException.class)
    @Transactional
    public void dealInvalidUserTest() throws GameException {
        gameManager.deal(40, 100);
    }

    @Test(expected = GameCouldNotBeStartedException.class)
    @Transactional
    public void invalidDealTest() throws GameException {
        gameManager.deal(1, 10000);
    }

    @Test
    @Transactional
    public void hitTest() throws Exception {
        GameResponse gameResponse = gameManager.deal(1, 50);
        GameResponse hitResponse = gameManager.hit(gameResponse.getId());
        assertEquals(50, hitResponse.getBet());
        assertNotSame(hitResponse.getPlayerCards(), hitResponse.getDealersCards());
    }

    @Test(expected = GameContextNotFoundException.class)
    @Transactional
    public void invalidGameIdOnHitTest() throws Exception {
        gameManager.deal(1, 50);
        gameManager.hit("gameID");
    }

    @Test(expected = GameContextNotFoundException.class)
    @Transactional
    public void invalidGameIdOnStandTest() throws Exception {
        gameManager.deal(1, 50);
        gameManager.stand("gameID");
    }

    @Test
    @Transactional
    public void standTest() throws Exception {
        GameResponse gameResponse = gameManager.deal(1, 50);
        GameResponse hitResponse = gameManager.stand(gameResponse.getId());
        assertEquals(50, hitResponse.getBet());
        assertNotSame(hitResponse.getPlayerCards(), hitResponse.getDealersCards());
    }

    @Test
    public void playerHasBlackjackTest() throws Exception {
        List<Card> playerCards = new ArrayList<Card>();
        playerCards.add(new Card(SuitType.CLUB, RankType.TEN));
        playerCards.add(new Card(SuitType.HEART, RankType.ACE));

        List<Card> dealersCards = new ArrayList<Card>();
        dealersCards.add(new Card(SuitType.HEART, RankType.QUEEN));

        GameContext gameContext = new GameContext(10, playerCards, dealersCards, null, 1);
        gameManager.getActiveGames().put(gameContext.getId(), gameContext);
        assertEquals(GameResult.WIN, gameContext.getGameResult());
    }

    @Test
    @Transactional
    public void playerBustedTest() throws Exception {
        List<Card> deck = deckService.getNewDeck(true);
        List<Card> playerCards = new ArrayList<Card>();
        playerCards.add(new Card(SuitType.CLUB, RankType.TEN));
        playerCards.add(new Card(SuitType.SPADE, RankType.TEN));
        playerCards.add(new Card(SuitType.HEART, RankType.ACE));

        List<Card> dealersCards = new ArrayList<Card>();
        dealersCards.add(new Card(SuitType.HEART, RankType.QUEEN));

        GameContext gameContext = new GameContext(10, playerCards, dealersCards, deck, 1);
        gameManager.getActiveGames().put(gameContext.getId(), gameContext);
        gameManager.hit(gameContext.getId());
        assertEquals(GameResult.LOOSE, gameContext.getGameResult());
    }

    @Test
    public void pushTest() throws Exception {
        List<Card> playerCards = new ArrayList<Card>();
        playerCards.add(new Card(SuitType.CLUB, RankType.QUEEN));
        playerCards.add(new Card(SuitType.HEART, RankType.KING));

        List<Card> dealersCards = new ArrayList<Card>();
        dealersCards.add(new Card(SuitType.HEART, RankType.JACK));
        dealersCards.add(new Card(SuitType.HEART, RankType.FOUR));
        dealersCards.add(new Card(SuitType.HEART, RankType.SIX));

        GameContext gameContext = new GameContext(10, playerCards, dealersCards, null, 1);
        gameManager.getActiveGames().put(gameContext.getId(), gameContext);
        assertEquals(GameResult.PUSH, gameContext.getGameResult());
    }

    @Test
    public void has21AfterHitTest()  {
        List<Card> deck = new ArrayList<Card>();
        deck.add(new Card(SuitType.HEART, RankType.ACE));

        List<Card> playerCards = new ArrayList<Card>();
        playerCards.add(new Card(SuitType.CLUB, RankType.TEN));

        List<Card> dealersCards = new ArrayList<Card>();
        dealersCards.add(new Card(SuitType.HEART, RankType.JACK));
        dealersCards.add(new Card(SuitType.HEART, RankType.FOUR));
        dealersCards.add(new Card(SuitType.HEART, RankType.SIX));

        GameContext gameContext = new GameContext(10, playerCards, dealersCards, deck, 1);
        gameManager.getActiveGames().put(gameContext.getId(), gameContext);
        try {
            gameManager.hit(gameContext.getId());
        } catch (GameException e) {
            e.printStackTrace();
        }
        assertEquals(GameResult.WIN, gameContext.getGameResult());
    }
}
