package vi.talii.view;

import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;
import vi.talii.service.GameManager;
import vi.talii.service.PlayerService;
import vi.talii.service.PlayerServiceImpl;
import java.util.Scanner;


public class ConsoleView implements View {

    private Scanner scanner = new Scanner(System.in);
    private PlayerService playerService = new PlayerServiceImpl();
    private Player player;
    private GameManager manager = new GameManager();

    public void start() {
        while (true) {
            System.out.println("Enter your acoount id, please");
            int playerId;
            try {
                playerId = scanner.nextInt();

                    player = manager.confirmPlayer(playerId);
                    while (true) {
                        manager.setPlayer(player);
                        showMainMenu();
                        int choice = scanner.nextInt();
                        doGeneralActionChoise(choice);
                    }
                } catch (NoSuchPlayerException e) {
                    e.printStackTrace();

                }
        }

    }

    private void showMainMenu(){
        System.out.println("Hi. Your cash is " + player.getCash() + "usd");
        System.out.println("1. Play");
        System.out.println("2. Fill up your cash");
    }

    public void doGeneralActionChoise(int choice) {
        switch (choice) {
            case 1:
                manager.play();
                break;
            case 2:
                fillUpCash();
                break;
            default:
                System.out.println("Wrong choice");
                break;
        }
    }

    private void fillUpCash() {
        System.out.println("Enter please money to upfill your cash");
        player.setCash(player.getCash() + scanner.nextInt());
        playerService.updatePlayersCash(player.getAccount_id(), player.getCash());
    }

}
