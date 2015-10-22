package vi.talii.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import vi.talii.exception.GameContextNotFoundException;
import vi.talii.exception.GameCouldNotBeStartedException;
import vi.talii.exception.GameException;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.*;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;
import static org.junit.Assert.*;


public class GameServiceTest {


    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:app-context.xml");
    private GameManager gameManager = applicationContext.getBean(GameManager.class);
    private DeckService deckService = applicationContext.getBean(DeckService.class);

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

        GameResponce gameResponce = gameManager.deal(1, 100);
        assertNotNull(gameResponce);
        assertEquals(100, gameResponce.getBet());
        assertNotSame(gameResponce.getPlayerCards(), gameResponce.getDealersCards());
        assertTrue(gameManager.getActiveGames().containsKey(gameResponce.getId()));
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
        GameResponce gameResponse = gameManager.deal(1, 50);
        GameResponce hitResponse = gameManager.hit(gameResponse.getId());
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
        GameResponce gameResponse = gameManager.deal(1, 50);
        GameResponce hitResponse = gameManager.stand(gameResponse.getId());
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
    public void has21AfterHitTest() throws Exception {
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
        gameManager.hit(gameContext.getId());
        assertEquals(GameResult.WIN, gameContext.getGameResult());
    }
}
