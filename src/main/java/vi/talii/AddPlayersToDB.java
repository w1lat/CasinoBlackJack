package vi.talii;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vi.talii.dao.PlayerDao;
import vi.talii.model.Player;

/**
 * Created by Vitalii on 12.10.2015.
 */
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
    }
}
