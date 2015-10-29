package vi.talii.dao;

import vi.talii.model.to.Transaction;

import java.util.List;


public interface TransactionDao {

    List<Transaction> findByPlayerId(long playerId);

    void save(Transaction transaction);

    List<Transaction> findAll();

}
