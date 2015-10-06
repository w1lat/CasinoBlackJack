package vi.talii.view;

import vi.talii.dao.StoragePlayerDao;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Card;
import vi.talii.model.Player;
import vi.talii.service.SetServiceImpl;
import vi.talii.storage.PlayerStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ConsoleView implements View {

    private Scanner scanner = new Scanner(System.in);

    private PlayerStorage playerStorage = new PlayerStorage();
    private StoragePlayerDao storagePlayerDao = new StoragePlayerDao(playerStorage);

    private Player player;
    private Card card;
    private Card dealerCard;
    private int playerCount = 0;
    private int dealerCount = 0;
    private List<Card> shuffledSet = new ArrayList<Card>();

    public void start() {
        while (true) {
            System.out.println("Enter your acoount id, please");
            int playerId;
            try {
                playerId = scanner.nextInt();

                    player = storagePlayerDao.getPlayerById(playerId);
                    while (true) {
                        System.out.println("Hi. Your cash is " + player.getCash() + "usd");
                        System.out.println("1. Play");
                        System.out.println("2. Fill up your cash");
                        int choice = scanner.nextInt();
                        doGeneralActionChoise(choice);
                    }
                } catch (NoSuchPlayerException e) {
                    e.printStackTrace();

                }
        }

    }

    private void doGeneralActionChoise(int choice) {
        switch (choice) {
            case 1:
                play();
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
            player.setCash(player.getCash() + scanner.nextInt());
    }

    private void play() {
            while (this.player.getCash() > 0) {
                System.out.println("Make your bet");
                int currBet = scanner.nextInt();
                if (currBet <= player.getCash()) {
                    player.setCash(player.getCash() - currBet);
                    storagePlayerDao.updatePlayersCash(player.getAccount_id(), player.getCash());
                    shuffledSet = SetServiceImpl.shuffle();
                    System.out.println(shuffledSet.size());
//                    Сдаем карты игроку
                    playerCount = 0;
                    dealerCount = 0;
                    card = SetServiceImpl.getTopCard(shuffledSet);
                    playerCount += card.getCount();
                    System.out.println("you got " + card.getIndex() + " " + card.getSuit());
                    dealerCard = SetServiceImpl.getTopCard(shuffledSet);
                    dealerCount += dealerCard.getCount();
                    System.out.println("dealer got " + dealerCard.getIndex() + " " + dealerCard.getSuit());
                    card = SetServiceImpl.getTopCard(shuffledSet);
                    playerCount += card.getCount();
                    System.out.println("you got " + card.getIndex() + " " + card.getSuit());
                    System.out.println("you have " + playerCount + "points");
                    dealerCard = SetServiceImpl.getTopCard(shuffledSet);
                    System.out.println("dealer has " + dealerCount + "points");
                    dealerCount += dealerCard.getCount();
                    playersGame();
                    if (playerCount <= 21) {
                        System.out.println("dealer got " + dealerCard.getIndex() + " " + card.getSuit());
                        System.out.println("dealer has " + dealerCount + "points");
                        dealersGame();
                        if (dealerCount <= 21) {
                            if (dealerCount == playerCount) {
                                System.out.println("push");
                                player.setCash(player.getCash() + currBet);
                                System.out.println("your cash is " + player.getCash());
                            } else if (dealerCount < playerCount) {
                                System.out.println("you won");
                                player.setCash(player.getCash() + currBet * 2);
                                System.out.println("your cash is " + player.getCash());
                            } else {
                                System.out.println("dealer won");
                                System.out.println("your cash is " + player.getCash());
                            }
                        } else {
                            System.out.println("you won");
                            player.setCash(player.getCash() + currBet * 2);
                            System.out.println("your cash is " + player.getCash());
                        }
                    } else {
                        System.out.println("you bust");
                        System.out.println("dealer got " + dealerCard.getIndex() + " " + card.getSuit());
                        System.out.println("dealer has " + dealerCount + "points");
                        System.out.println("dealer won");
                        System.out.println("your cash is " + player.getCash());
                    }
                } else {
                    System.out.println("You have not enought cash on your account");
                }
            }
        System.out.println("Sorry but your cash is 0");
    }

    private void doStopHitChoise(int choice) {
        switch (choice) {
            case 1:
                card = SetServiceImpl.getTopCard(shuffledSet);
                System.out.println(shuffledSet.size());
                playerCount += card.getCount();
                System.out.println("you got " + card.getIndex() + " " + card.getSuit());
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
            while (playerCount < 21 && choice == 1) {
                System.out.println("press 1 to hit, press 2 to stop");
                choice = scanner.nextInt();
                doStopHitChoise(choice);
            }
    }

    private void dealersGame() {
        while (dealerCount <= 17) {
            dealerCard = SetServiceImpl.getTopCard(shuffledSet);
            dealerCount += dealerCard.getCount();
            System.out.println("dealer got " + dealerCard.getIndex() + " " + card.getSuit());
            System.out.println("dealer has " + dealerCount + "points");
        }
    }
}
