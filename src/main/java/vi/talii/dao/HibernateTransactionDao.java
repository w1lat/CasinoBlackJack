package vi.talii.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vi.talii.model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;


@Repository
public class HibernateTransactionDao implements TransactionDao {

    public static final Logger LOG = Logger.getLogger(HibernateTransactionDao.class);

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public HibernateTransactionDao() {
    }

    public List<Transaction> findByPlayerId(long playerId) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<Transaction> query = manager.createQuery("FROM Transaction t WHERE t.playerId = :playerId", Transaction.class)
                .setParameter("playerId", playerId);
        List<Transaction> transactions = query.getResultList();
        return transactions;
    }

    public void save(Transaction transaction) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
           entityTransaction.begin(); //
            entityManager.persist(transaction);
            entityTransaction.commit();//
        }catch (Exception e){
            LOG.error(e);
            entityTransaction.rollback();
        }
        entityManager.close();
    }


    public List<Transaction> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Transaction> query = entityManager.createQuery("SELECT t FROM Transaction t", Transaction.class);
        List<Transaction> all = query.getResultList();
        return all;
    }
}
