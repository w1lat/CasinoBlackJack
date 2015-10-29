package vi.talii.service;

import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.to.Player;
import vi.talii.model.TransactionType;

import java.util.List;


public interface PlayerService {


    List<Player> findAll();

    Player find(long account) throws NoSuchPlayerException;

    boolean canPlay(long account, int bet) throws NoSuchPlayerException;

    void addFunds(long account, double funds, TransactionType transactionType) throws NoSuchPlayerException;

    void takeFunds(long account, int funds) throws NoSuchPlayerException;

    List<vi.talii.model.to.Transaction> findAllTransactionsByPlayer(long account) throws NoSuchPlayerException;

    List<vi.talii.model.to.Transaction> findAllTransactions();
}
