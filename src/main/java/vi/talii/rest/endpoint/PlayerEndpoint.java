package vi.talii.rest.endpoint;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;
import vi.talii.model.Transaction;
import vi.talii.model.TransactionType;
import vi.talii.service.PlayerService;


import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/blackjack/players")//todo remove duplicates in methods path
public class PlayerEndpoint extends InitServicesBase {

    private static final Logger LOGGER = Logger.getLogger(PlayerEndpoint.class);

    public PlayerEndpoint() {
    }


    @GET
    @Produces(MediaType.TEXT_HTML)
    public List<Player> findAll() {
        LOGGER.info("Receive 'Find all' players request");
        return playerService.findAll();
    }

    @PUT
    @Produces(MediaType.TEXT_HTML)
    @Path("/blackjack/players/{account}/{cash}")
    public void addFunds(@PathParam("account") Long account, @PathParam("cash") int cash) throws NoSuchPlayerException {
        LOGGER.info("Receive 'Add cash' request for account " + account +  " to add " + cash + " funds");
        playerService.addFunds(account, cash, TransactionType.INCOME);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/blackjack/players/{account}")
    public Player findById(@PathParam("account") Long account) throws NoSuchPlayerException {
        LOGGER.info("Receive 'Find Player By ID' request for account " + account);
        return playerService.find(account);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/blackjack/players/{account}/transactions")
    public List<Transaction> showTransactionsHistory(@PathParam("account") Long account) throws
            NoSuchPlayerException {
        LOGGER.info("Receive 'Get player transactions' request for account " + account);
        return playerService.findAllTransactionsByPlayer(account);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/blackjack/transactions")
    public List<Transaction> findAllTransactions() {
        LOGGER.info("Receive 'Find all transactions' request");
        return playerService.findAllTransactions();
    }

}
