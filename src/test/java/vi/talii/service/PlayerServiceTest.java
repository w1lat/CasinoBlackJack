package vi.talii.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;
import vi.talii.model.TransactionType;

import java.util.List;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

// TODO use logger dont throw exception
public class PlayerServiceTest extends InitAppContextBase {

    private static final int BET = 100;


//    автовайред TODO
//    @Autowired
//    PlayerService playerService;

    @Test
    public void findAllTest() {
        List<Player> players = playerService.findAll();
        assertNotNull(players);
        assertEquals(2, players.size());
    }

    @Test
    public void canPlayTest() throws NoSuchPlayerException {
        List<Player> players = playerService.findAll();
        Player player = players.get(0);
        int newBet = (int)player.getBalance() - 1;
        boolean canPlay = playerService.canPlay(player.getId(), newBet);
        assertTrue(canPlay);
    }

    @Test
    public void cantPlayTest() throws NoSuchPlayerException {
        List<Player> players = playerService.findAll();
        Player player = players.get(0);
        int newBet = (int)player.getBalance() + 1;
        boolean canPlay = playerService.canPlay(player.getId(), newBet);
        assertFalse(canPlay);
    }

    @Test(expected = NoSuchPlayerException.class)
    public void canPlayInvalidUserTest() throws NoSuchPlayerException {
        boolean canPlay = playerService.canPlay(150, BET);
        assertFalse(canPlay);
    }

    @Test
    public void test_Add_Funds() throws NoSuchPlayerException {
        List<Player> players = playerService.findAll();
        Player player = players.get(0);
        double currentBalance = player.getBalance();
        playerService.addFunds(player.getId(), BET, TransactionType.INCOME);
        Player player1 = playerService.find(player.getId());
        assertEquals(currentBalance + BET, player1.getBalance());
    }
}
