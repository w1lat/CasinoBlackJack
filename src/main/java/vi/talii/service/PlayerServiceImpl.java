package vi.talii.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vi.talii.dao.PlayerDao;
import vi.talii.dao.TransactionDao;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.to.Player;
import vi.talii.model.to.Transaction;
import vi.talii.model.TransactionType;

import java.util.List;


@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private static final Logger LOGGER = Logger.getLogger(PlayerServiceImpl.class);

    private PlayerDao playerDao;

    private TransactionDao transactionDao;


    @Autowired
    public PlayerServiceImpl(PlayerDao playerDao, TransactionDao transactionDao) {
        this.playerDao = playerDao;
        this.transactionDao = transactionDao;
    }

    public List<Player> findAll() {
        LOGGER.info("Searching all players...");
        return playerDao.findAll();
    }

    public Player find(long account) throws NoSuchPlayerException {
        LOGGER.info("Finding a player with id: " + account);

        Player player = playerDao.getPlayerById(account);

        if (player == null) {
            throw new NoSuchPlayerException("No player found with account id: " + account);
        }
        return player;
    }

    public boolean canPlay(long account, int bet) throws NoSuchPlayerException {
        LOGGER.info("Checking is player with id " + account + " can make a bet in amount " + bet + " and play");
        Player player = find(account);
        double currentBalance = player.getBalance();
        if (currentBalance > bet) {
            double newBalance = currentBalance - bet;
            player.setBalance(newBalance);
            playerDao.updatePlayer(player);
            Transaction transaction = new Transaction(player.getId(), TransactionType.BET, bet);
            transactionDao.save(transaction);
            return true;
        }
        return false;
    }

    public void addFunds(long account, double funds, TransactionType transactionType) throws NoSuchPlayerException {
        Player player = find(account);
        double currentBalance = player.getBalance();
        double newBalance = currentBalance + funds;
        player.setBalance(newBalance);
        Transaction transaction = new Transaction(player.getId(), transactionType, funds);
        transactionDao.save(transaction);
        playerDao.updatePlayer(player);
    }

    public void takeFunds(long account, int funds) throws NoSuchPlayerException {
        Player player = find(account);
        double currentBalance = player.getBalance();
        double newBalance = currentBalance - funds;
        player.setBalance(newBalance);
        Transaction transaction = new Transaction(player.getId(), TransactionType.BET, funds);
        transactionDao.save(transaction);
        playerDao.updatePlayer(player);
    }

    public List<Transaction> findAllTransactionsByPlayer(long account) throws NoSuchPlayerException {
        List<Transaction> playerTransactions = transactionDao.findByPlayerId(account);
        if (playerTransactions != null && !playerTransactions.isEmpty()) {
            return playerTransactions;
        } else {
            throw new NoSuchPlayerException("Player not found");
        }
    }

    public List<Transaction> findAllTransactions() {
        return transactionDao.findAll();
    }


}
