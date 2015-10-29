package vi.talii;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vi.talii.dao.PlayerDao;
import vi.talii.dao.TransactionDao;
import vi.talii.model.to.Player;
import vi.talii.model.to.Transaction;
import vi.talii.model.TransactionType;

public class AddPlayersToDB {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:app-context.xml");
        PlayerDao playerDao = applicationContext.getBean(PlayerDao.class);
        Player player = new Player();
        player.setBalance(5000);
        playerDao.addNewPlayer(player);
        System.out.println(playerDao.findAll());
        Player player1 = new Player();
        player1.setBalance(1000);
        playerDao.addNewPlayer(player1);
        Player player2 = new Player();
        player2.setBalance(3000);
        playerDao.addNewPlayer(player2);
        Player player3 = new Player();
        player3.setBalance(6000);
        playerDao.addNewPlayer(player3);
        System.out.println(playerDao.findAll());

        TransactionDao transactionDao = applicationContext.getBean(TransactionDao.class);

        Transaction transaction = new Transaction();
        transaction.setAmount(200);
        transaction.setPaymentType(TransactionType.BET);
        transaction.setPlayerId(2L);
        transactionDao.save(transaction);
        System.out.println(transactionDao.findAll());
        System.out.println(transactionDao.findByPlayerId(2L));
        Transaction transaction1 = new Transaction();
        transaction1.setAmount(500);
        transaction1.setPaymentType(TransactionType.WIN);
        transaction1.setPlayerId(2L);
        transactionDao.save(transaction1);
        Transaction transaction2 = new Transaction();
        transaction2.setAmount(600);
        transaction2.setPaymentType(TransactionType.INCOME);
        transaction2.setPlayerId(2L);
        transactionDao.save(transaction2);
        Transaction transaction3 = new Transaction();
        transaction3.setAmount(200);
        transaction3.setPaymentType(TransactionType.PUSH);
        transaction3.setPlayerId(2L);
        transactionDao.save(transaction3);
        System.out.println(transactionDao.findAll());
        System.out.println(transactionDao.findByPlayerId(2L));


    }
}
