package vi.talii.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vi.talii.exception.GameContextNotFoundException;
import vi.talii.exception.GameCouldNotBeStartedException;
import vi.talii.exception.GameException;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GameManager {

    private static final Logger LOGGER = Logger.getLogger(GameManager.class);

    private PlayerService playerService;

    private DeckService deckService;

    private Map<String, GameContext> activeGames = new HashMap<String, GameContext>();

    private String currentGameId;

    @Autowired
    public GameManager(PlayerService playerService, DeckService deckService) {
        this.playerService = playerService;
        this.deckService = deckService;
    }

    public GameResponce deal(long account, int bet) throws GameException{
        LOGGER.info("Starting new blackjack game for account " + account +  " with bet " + bet);
        if (playerService.canPlay(account, bet)) {
            GameContext gameContext = createNewGame(account, bet);
            return buildGameResponce(gameContext);
        } else {
            throw new GameCouldNotBeStartedException("Game could not be started, because player with id: " + account
                    + ", is not allowed to play");
        }
    }

    public GameResponce stand(String gameId) throws GameException {
        if (activeGames.containsKey(gameId)) {
            GameContext gameContext = activeGames.get(gameId);
            LOGGER.info("Found deal. Executing STAND command");
            dealerTurn(gameContext);
            GameResponce gameResponce = buildGameResponce(gameContext);
            gameResponce.setGameResult(gameContext.getGameResult());
            return gameResponce;
        } else {
            throw new GameContextNotFoundException();
        }
    }

    public GameResponce hit(String gameId) throws GameException {
        if (activeGames.containsKey(gameId)) {
            GameContext gameContext = activeGames.get(gameId);
            LOGGER.info("Found deal. Executing HIT command");
            gameContext.getPlayerCards().add(deckService.dealNextCard(gameContext.getDeck()));
            if (gameContext.isPlayerBusted()) {
                gameContext.getDealerCards().add(deckService.dealNextCard(gameContext.getDeck()));
                return playerLost(gameContext);
            } else if (gameContext.getPlayerPoints() == 21) {
                return dealerTurn(gameContext);
            }
            return buildGameResponce(gameContext);
        } else {
            throw new GameContextNotFoundException();
        }
    }


    private GameResponce dealerTurn(GameContext gameContext) throws NoSuchPlayerException {
        LOGGER.info("Simulate dealer's behavior...");
        while (gameContext.getDealerPoints() < 17) {
            gameContext.getDealerCards().add(deckService.dealNextCard(gameContext.getDeck()));
        }
        if (gameContext.getGameResult() == GameResult.WIN) {
            activeGames.remove(gameContext.getId());
            return playerWins(gameContext);
        } else if (gameContext.getGameResult() == GameResult.LOOSE) {
            activeGames.remove(gameContext.getId());
            return playerLost(gameContext);
        } else {
            activeGames.remove(gameContext.getId());
            return push(gameContext);
        }
    }

    private GameResponce playerLost(GameContext gameContext) throws NoSuchPlayerException {
        GameResponce gameResponce = buildGameResponce(gameContext);
        gameResponce.setGameResult(gameContext.getGameResult());
        return gameResponce;
    }

    private GameResponce playerWins(GameContext gameContext) throws NoSuchPlayerException {
        int bet = gameContext.getBet();
        int winBonus = bet * 2;
        playerService.addFunds(gameContext.getAccount(), winBonus, TransactionType.WIN);
        GameResponce gameResponce = buildGameResponce(gameContext);
        gameResponce.setGameResult(gameContext.getGameResult());
        return gameResponce;
    }

    private GameResponce buildGameResponce(GameContext gameContext) {
        LOGGER.info("Building GameResponse...");
        String id = gameContext.getId();
        List<Card> playerCards = gameContext.getPlayerCards();
        List<Card> dealerCards = gameContext.getDealerCards();
        int playerPoints = gameContext.getPlayerPoints();
        int dealerPoints = gameContext.getDealerPoints();
        int bet = gameContext.getBet();
        return new GameResponce(id, playerCards, dealerCards, playerPoints, dealerPoints, bet);
    }

    private GameContext createNewGame(long account, int bet) throws NoSuchPlayerException {
        LOGGER.info("Building new GameContext...");
        List<Card> deck = deckService.getNewDeck(true);
        List<Card> playersCards = new ArrayList<Card>();
        List<Card> dealersCards = new ArrayList<Card>();
        playersCards.add(deckService.dealNextCard(deck));
        playersCards.add(deckService.dealNextCard(deck));
        dealersCards.add(deckService.dealNextCard(deck));

        GameContext gameContext = new GameContext(bet, playersCards, dealersCards, deck, account);
        activeGames.put(gameContext.getId(), gameContext);

        currentGameId = gameContext.getId();

        if (gameContext.isPlayerBlackjack()) {
            dealersCards.add(deckService.dealNextCard(deck));
            if (gameContext.isDealerBlackjack()) {
                push(gameContext);
            } else {
                evaluatePlayersBlackjack(gameContext);
            }
        }
        return gameContext;
    }

    private GameResponce push(GameContext gameContext) throws NoSuchPlayerException {
        playerService.addFunds(gameContext.getAccount(), gameContext.getBet(), TransactionType.PUSH);
        GameResponce gameResponce = buildGameResponce(gameContext);
        gameResponce.setGameResult(gameContext.getGameResult());
        return gameResponce;
    }

    private void evaluatePlayersBlackjack(GameContext gameContext) throws NoSuchPlayerException {
        int bet = gameContext.getBet();
        double blackjackBonus = bet * 1.5;
        int winBonus = (int) (blackjackBonus + bet);
        playerService.addFunds(gameContext.getAccount(), winBonus, TransactionType.WIN);
        GameResponce gameResponce = buildGameResponce(gameContext);
        gameResponce.setGameResult(gameContext.getGameResult());
    }

    public Map<String, GameContext> getActiveGames() {
        return activeGames;
    }

    public String getCurrentGameId() {
        return currentGameId;
    }
}




