package vi.talii.rest.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import vi.talii.exception.GameException;
import vi.talii.model.GameResponce;
import vi.talii.service.GameManager;

import javax.swing.text.html.HTML;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/blackjack")
public class GameController {

    private static final Logger LOGGER = Logger.getLogger(GameController.class);

    private GameManager gameManager;

    @Autowired
    public GameController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/deal/{account}/{bet}")
    public GameResponce deal(@PathParam("account") Long account, @PathParam("bet") int bet) throws GameException {
        LOGGER.info("Receive request to start blackjack game for account " + account + " with bet: " + bet);
        return gameManager.deal(account, bet);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{gameId}/stand")
    public GameResponce stand(@PathParam("gameId") String gameId) throws GameException {
        LOGGER.info("Receive 'STAND' request for deal " + gameId);
        return gameManager.stand(gameId);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{gameId}/hit")
    public GameResponce hit(@PathParam("gameId") String gameId) throws GameException {
        LOGGER.info("Receive 'HIT' request for deal " + gameId);
        return gameManager.hit(gameId);
    }

}
