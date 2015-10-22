package vi.talii.dao;

import vi.talii.model.Transaction;

import javax.swing.text.TabableView;
import java.awt.event.ItemListener;
import java.util.List;


public interface TransactionDao {

    List<Transaction> findByPlayerId(long playerId);
    void save(Transaction transaction);
    List<Transaction> findAll();

}
