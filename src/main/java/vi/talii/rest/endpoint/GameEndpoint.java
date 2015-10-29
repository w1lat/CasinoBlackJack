package vi.talii.rest.endpoint;

import org.apache.log4j.Logger;
import vi.talii.exception.GameException;
import vi.talii.model.to.GameResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/blackjack")
public class GameEndpoint extends InitServicesBase {

    private static final Logger LOGGER = Logger.getLogger(GameEndpoint.class);

    public GameEndpoint() {
    }

    @GET
    @Produces("application/json")
    @Path("/deal/{account}/{bet}")
    public GameResponse deal(@PathParam("account") Long account, @PathParam("bet") int bet) throws GameException {
        String message;
        LOGGER.info("Receive request to start blackjack game for account " + account + " with bet: " + bet);
        try {
            return gameManager.deal(account, bet);
        } catch (GameException e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        throw new GameException(message);
    }

    @GET
    @Produces("application/json")
    @Path("/{gameId}/stand")
    public GameResponse stand(@PathParam("gameId") String gameId) throws GameException {
        LOGGER.info("Receive 'STAND' request for deal " + gameId);
        return gameManager.stand(gameId);
    }

    @GET
    @Produces("application/json")
    @Path("/{gameId}/hit")
    public GameResponse hit(@PathParam("gameId") String gameId) throws GameException {
        LOGGER.info("Receive 'HIT' request for deal " + gameId);
        return gameManager.hit(gameId);
    }

}
