package vi.talii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Card;
import vi.talii.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class GameManager {

    public static final int POINT_LIMIT = 21;
    private Scanner scanner = new Scanner(System.in);

    @Autowired
    private PlayerService playerService;

    private Player player;
    private Card card;
    private int playerCount = 0;
    private int dealerCount = 0;
    private List<Card> currentDeck = new ArrayList<Card>();
    private int currBet;

    public GameManager() {
    }

    public GameManager(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player confirmPlayer(int playerId) throws NoSuchPlayerException {
        Player player = playerService.getPlayerById(playerId);
        return player;
    }

    public void play() {
        while (this.player.getCash() > 0) {
            System.out.println("Make your bet");
            currBet = scanner.nextInt();
            if (currBet <= player.getCash()) {
                player.setCash(player.getCash() - currBet);
                currentDeck = SetServiceImpl.shuffle();
//                    Обнуляем счета предыдущих игр
                playerCount = 0;
                dealerCount = 0;
//                    Сдаем карты


                playerCount += giveCard("you", true);//дать карту игроку, показать ее номинал
                dealerCount += giveCard("dealer", true);//дать карту диллеру и показать ее номинал
                playerCount += giveCard("you", true);//дать карту игроку, показать ее номинал
                System.out.println("you have " + playerCount + "points");//показать количество очков у игрока
                dealerCount += giveCard("dealer", false);//дать карту диллеру, не показывать ее номинал
                Card dealerLastCard = card;
                System.out.println("dealer has " + (dealerCount - dealerLastCard.getCount()) + "points");//показать очки диллера без второй карты

                if (checkForBlackJackExist()) {
                    BlackJackProcess(); //обаработка данных если у кого то есть BlackJack
                } else {
                    playersGame();
                    if (checkForUserNotBusted()) {
                        System.out.println("dealer got " + dealerLastCard.getIndex() + " " + card.getSuit());
                        System.out.println("dealer has " + dealerCount + "points");
                        dealersGame();
                        if (checkForDealerNotBusted()) {
                            processGameResult(); //обработка данных по окончанию игры (сравнивание очков, определение победителя)
                        } else {
                            System.out.println("you won");
                            player.setCash(player.getCash() + currBet * 2);
                            playerService.updatePlayersCash(player.getId(), player.getCash());
                            System.out.println("your cash is " + player.getCash());
                        }
                    } else {
                        System.out.println("you bust");
                        System.out.println("dealer got " + dealerLastCard.getIndex() + " " + card.getSuit());
                        System.out.println("dealer has " + dealerCount + "points");
                        System.out.println("dealer won");
                        System.out.println("your cash is " + player.getCash());
                        playerService.updatePlayersCash(player.getId(), player.getCash());
                    }
                }
            } else {
                System.out.println("You have not enought cash on your account");
            }
        }
        System.out.println("Sorry but your cash is 0");
    }

    private void processGameResult() {
        if (checkForPush()) {
            System.out.println("push");
            player.setCash(player.getCash() + currBet);
            playerService.updatePlayersCash(player.getId(), player.getCash());
            System.out.println("your cash is " + player.getCash());
        } else if (checkForUserWin()) {
            System.out.println("you won");
            player.setCash(player.getCash() + currBet * 2);
            playerService.updatePlayersCash(player.getId(), player.getCash());
            System.out.println("your cash is " + player.getCash());
        } else {
            System.out.println("dealer won");
            System.out.println("your cash is " + player.getCash());
            playerService.updatePlayersCash(player.getId(), player.getCash());
        }
    }

    private void BlackJackProcess() {
        if (checkForBlackJackPush()) {
            System.out.println("push");
            player.setCash(player.getCash() + currBet);
            playerService.updatePlayersCash(player.getId(), player.getCash());
            System.out.println("your cash is " + player.getCash());
        } else if (checkForUsersBlackJack()) {
            System.out.println("You got BlackJack!!!");
            player.setCash(player.getCash() + currBet * 2.5);
            playerService.updatePlayersCash(player.getId(), player.getCash());
            System.out.println("your cash is " + player.getCash());
        } else if (checkForDealersBlackJack()) {
            System.out.println("dealer has BlackJack!!!");
            System.out.println("your cash is " + player.getCash());
            playerService.updatePlayersCash(player.getId(), player.getCash());
        }
    }

    private boolean checkForUserWin() {
        return dealerCount < playerCount;
    }

    private boolean checkForPush() {
        return dealerCount == playerCount;
    }

    private boolean checkForDealerNotBusted() {
        return dealerCount <= POINT_LIMIT;
    }

    private boolean checkForUserNotBusted() {
        return playerCount <= POINT_LIMIT;
    }

    private boolean checkForDealersBlackJack() {
        return dealerCount == POINT_LIMIT;
    }

    private boolean checkForUsersBlackJack() {
        return playerCount == POINT_LIMIT;
    }

    private boolean checkForBlackJackPush() {
        return checkForUsersBlackJack() && checkForDealersBlackJack();
    }

    private boolean checkForBlackJackExist() {
        return checkForUsersBlackJack() || checkForDealersBlackJack();
    }

    private void doStopHitChoise(int choice) {
        switch (choice) {
            case 1:
                playerCount += giveCard("you", true);//дать карту игроку, показать ее номинал
                System.out.println("you have " + playerCount + "points");
                break;
            case 2:
                break;
            default:
                System.out.println("Wrong choice");
                break;
        }
    }

    private void playersGame() {
        int choice = 1;
        while (playerCount < POINT_LIMIT && choice != 2) {
            System.out.println("press 1 to hit, press 2 to stop");
            choice = scanner.nextInt();
            doStopHitChoise(choice);
        }
    }

    private void dealersGame() {
        while (dealerCount <= 17) {
            dealerCount += giveCard("dealer", true);//дать карту диллеру и показать ее номинал
            System.out.println("dealer has " + dealerCount + "points");
        }
    }

    private int giveCard(String person, boolean showing) {
        card = SetServiceImpl.getTopCard(currentDeck);
        if (showing)
            System.out.println(person + " got " + card.getIndex() + " " + card.getSuit());
        return card.getCount();
    }
}