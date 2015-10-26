package vi.talii.rest.endpoint;

import org.apache.log4j.Logger;
import vi.talii.exception.GameException;
import vi.talii.model.Card;
import vi.talii.model.RankType;
import vi.talii.model.SuitType;
import vi.talii.model.to.GameResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/blackjack")
public class GameEndpoint extends InitServicesBase {

    private static final Logger LOGGER = Logger.getLogger(GameEndpoint.class);

    public GameEndpoint() {
    }

    @GET
    @Produces("application/json")
    @Path("/deal/{account}/{bet}")
    public GameResponse deal(@PathParam("account") Long account, @PathParam("bet") int bet) throws GameException {
        LOGGER.info("Receive request to start blackjack game for account " + account + " with bet: " + bet);
        try {
            //return gameManager.deal(account, bet);
            List<Card> playerCards = new ArrayList<Card>();
            playerCards.add(new Card(SuitType.CLUB, RankType.TEN));
            playerCards.add(new Card(SuitType.SPADE, RankType.TEN));
            playerCards.add(new Card(SuitType.HEART, RankType.ACE));

            List<Card> dealersCards = new ArrayList<Card>();
            dealersCards.add(new Card(SuitType.HEART, RankType.QUEEN));

            return new GameResponse("sdf",playerCards,dealersCards,3434,233,2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new GameException("tratar");
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{gameId}/stand")
    public GameResponse stand(@PathParam("gameId") String gameId) throws GameException {
        LOGGER.info("Receive 'STAND' request for deal " + gameId);
        return gameManager.stand(gameId);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{gameId}/hit")
    public GameResponse hit(@PathParam("gameId") String gameId) throws GameException {
        LOGGER.info("Receive 'HIT' request for deal " + gameId);
        return gameManager.hit(gameId);
    }

}
