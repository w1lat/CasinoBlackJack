package vi.talii.service;

import org.apache.log4j.Logger;
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

public class GameServiceTest extends InitAppContextBase {

    private static final Logger LOGGER = Logger.getLogger(GameServiceTest.class);

    public GameServiceTest() {
    }

    @Test
    @Transactional
    public void dealTest() {

        GameResponse gameResponse = null;
        try {
            gameResponse = gameManager.deal(1, 100);
        } catch (GameException e) {
            LOGGER.error(e);
        }
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
    public void hitTest() {
        GameResponse gameResponse = null;
        try {
            gameResponse = gameManager.deal(1, 50);
            GameResponse hitResponse = gameManager.hit(gameResponse.getId());
            assertEquals(50, hitResponse.getBet());
            assertNotSame(hitResponse.getPlayerCards(), hitResponse.getDealersCards());
        } catch (GameException e) {
            LOGGER.error(e);
        }
    }

    @Test(expected = GameContextNotFoundException.class)
    @Transactional
    public void invalidGameIdOnHitTest() throws GameException {
        gameManager.deal(1, 50);
        gameManager.hit("gameID");
    }

    @Test(expected = GameContextNotFoundException.class)
    @Transactional
    public void invalidGameIdOnStandTest() throws GameException {
        gameManager.deal(1, 50);
        gameManager.stand("gameID");
    }

    @Test
    @Transactional
    public void standTest() {
        GameResponse gameResponse = null;
        try {
            gameResponse = gameManager.deal(1, 50);
            GameResponse hitResponse = gameManager.stand(gameResponse.getId());
            assertEquals(50, hitResponse.getBet());
            assertNotSame(hitResponse.getPlayerCards(), hitResponse.getDealersCards());
        } catch (GameException e) {
            LOGGER.error(e);
        }
    }

    @Test
    public void playerHasBlackjackTest(){
        List<Card> playerCards = new ArrayList<>();
        playerCards.add(new Card(SuitType.CLUB, RankType.TEN));
        playerCards.add(new Card(SuitType.HEART, RankType.ACE));

        List<Card> dealersCards = new ArrayList<>();
        dealersCards.add(new Card(SuitType.HEART, RankType.QUEEN));

        GameContext gameContext = new GameContext(10, playerCards, dealersCards, null, 1);
        gameManager.getActiveGames().put(gameContext.getId(), gameContext);
        assertEquals(GameResult.WIN, gameContext.getGameResult());
    }

    @Test
    @Transactional
    public void playerBustedTest() {
        List<Card> deck = deckService.getNewDeck(true);
        List<Card> playerCards = new ArrayList<>();
        playerCards.add(new Card(SuitType.CLUB, RankType.TEN));
        playerCards.add(new Card(SuitType.SPADE, RankType.TEN));
        playerCards.add(new Card(SuitType.HEART, RankType.ACE));

        List<Card> dealersCards = new ArrayList<>();
        dealersCards.add(new Card(SuitType.HEART, RankType.QUEEN));

        GameContext gameContext = new GameContext(10, playerCards, dealersCards, deck, 1);
        gameManager.getActiveGames().put(gameContext.getId(), gameContext);
        try {
            gameManager.hit(gameContext.getId());
        } catch (GameException e) {
            LOGGER.error(e);
        }
        assertEquals(GameResult.LOOSE, gameContext.getGameResult());
    }

    @Test
    public void pushTest(){
        List<Card> playerCards = new ArrayList<>();
        playerCards.add(new Card(SuitType.CLUB, RankType.QUEEN));
        playerCards.add(new Card(SuitType.HEART, RankType.KING));

        List<Card> dealersCards = new ArrayList<>();
        dealersCards.add(new Card(SuitType.HEART, RankType.JACK));
        dealersCards.add(new Card(SuitType.HEART, RankType.FOUR));
        dealersCards.add(new Card(SuitType.HEART, RankType.SIX));

        GameContext gameContext = new GameContext(10, playerCards, dealersCards, null, 1);
        gameManager.getActiveGames().put(gameContext.getId(), gameContext);
        assertEquals(GameResult.PUSH, gameContext.getGameResult());
    }

    @Test
    public void has21AfterHitTest()  {
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(SuitType.HEART, RankType.ACE));

        List<Card> playerCards = new ArrayList<>();
        playerCards.add(new Card(SuitType.CLUB, RankType.TEN));

        List<Card> dealersCards = new ArrayList<>();
        dealersCards.add(new Card(SuitType.HEART, RankType.JACK));
        dealersCards.add(new Card(SuitType.HEART, RankType.FOUR));
        dealersCards.add(new Card(SuitType.HEART, RankType.SIX));

        GameContext gameContext = new GameContext(10, playerCards, dealersCards, deck, 1);
        gameManager.getActiveGames().put(gameContext.getId(), gameContext);
        try {
            gameManager.hit(gameContext.getId());
        } catch (GameException e) {
            LOGGER.error(e);
        }
        assertEquals(GameResult.WIN, gameContext.getGameResult());
    }
}
