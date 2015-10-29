package vi.talii.rest.endpoint;

import org.apache.log4j.Logger;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.to.Player;
import vi.talii.model.to.Transaction;
import vi.talii.model.TransactionType;

import javax.ws.rs.*;
import java.util.List;


@Path("/players")
public class PlayerEndpoint extends InitServicesBase {

    private static final Logger LOGGER = Logger.getLogger(PlayerEndpoint.class);

    public PlayerEndpoint() {
    }


    @GET
    @Produces("application/json")
    public List<Player> findAll() {
        LOGGER.info("Receive 'Find all' players request");
        return playerService.findAll();
    }

    @PUT
    @Path("/{account}/{cash}")
    public void addFunds(@PathParam("account") Long account, @PathParam("cash") int cash) throws NoSuchPlayerException {
        LOGGER.info("Receive 'Add cash' request for account " + account +  " to add " + cash + " funds");
        playerService.addFunds(account, cash, TransactionType.INCOME);
    }

    @GET
    @Produces("application/json")
    @Path("/{account}")
    public Player findById(@PathParam("account") Long account) throws NoSuchPlayerException {
        LOGGER.info("Receive 'Find Player By ID' request for account " + account);
        return playerService.find(account);
    }

    @GET
    @Produces("application/json")
    @Path("/{account}/transactions")
    public List<Transaction> showTransactionsHistory(@PathParam("account") Long account) throws
            NoSuchPlayerException {
        LOGGER.info("Receive 'Get player transactions' request for account " + account);
        return playerService.findAllTransactionsByPlayer(account);
    }

    @GET
    @Produces("application/json")
    @Path("/transactions")
    public List<Transaction> findAllTransactions() {
        LOGGER.info("Receive 'Find all transactions' request");
        return playerService.findAllTransactions();
    }

}
