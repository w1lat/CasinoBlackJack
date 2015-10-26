package vi.talii;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vi.talii.exception.GameException;
import vi.talii.model.GameResponce;
import vi.talii.service.GameManager;
import vi.talii.service.PlayerService;

import java.util.Scanner;

/**
 * Created by Vitalii on 06.10.2015.
 */
public class RunApp {

    public static void main(String[] args) throws GameException {
        Scanner scanner = new Scanner(System.in);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:app-context.xml");
        GameManager gameManager = applicationContext.getBean(GameManager.class);
        PlayerService playerService = applicationContext.getBean(PlayerService.class);

        System.out.println("Enter please player id");
        Long playerId = scanner.nextLong();
        while(true) {
            System.out.println("Enter your bet");
            int bet = scanner.nextInt();
            GameResponce gameResponce = gameManager.deal(playerId, bet);
            System.out.println("You have " + playerService.find(playerId).getBalance() + "usd");
            System.out.println("Dealers cards " + gameResponce.getDealersCards() + " " + gameResponce.getDealersScore() + "pts");
            System.out.println("Players cards " + gameResponce.getPlayerCards() + " " + gameResponce.getPlayersScore() + "pts");
            System.out.println(gameResponce.getGameResult());
            while (gameResponce.getGameResult() == null) {
                System.out.println("Press 1 to hit or 2 to stop");
                switch (scanner.nextInt()) {
                    case 1: {
                        gameResponce = gameManager.hit(gameResponce.getId());
                        System.out.println("Dealers cards " + gameResponce.getDealersCards() + " " + gameResponce.getDealersScore() + "pts");
                        System.out.println("Players cards " + gameResponce.getPlayerCards() + " " + gameResponce.getPlayersScore() + "pts");
                        System.out.println(gameResponce.getGameResult());
                        break;
                    }
                    case 2: {
                        gameResponce = gameManager.stand(gameResponce.getId());
                        System.out.println("Dealers cards " + gameResponce.getDealersCards() + " " + gameResponce.getDealersScore() + "pts");
                        System.out.println("Players cards " + gameResponce.getPlayerCards() + " " + gameResponce.getPlayersScore() + "pts");
                        System.out.println(gameResponce.getGameResult());
                        break;
                    }

                }
            }
            System.out.println("Your balance is " + playerService.find(playerId).getBalance() + "usd");
        }

    }
}
