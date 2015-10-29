package vi.talii.service;


import org.apache.log4j.Logger;
import org.junit.Test;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.to.Player;
import vi.talii.model.TransactionType;

import java.util.List;


import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class PlayerServiceTest extends InitAppContextBase {

    private static final Logger LOGGER = Logger.getLogger(PlayerServiceTest.class);
    private static final int BET = 100;


    @Test
    public void findAllTest() {
        List<Player> players = playerService.findAll();
        assertNotNull(players);
        assertEquals(2, players.size());
    }

    @Test
    public void canPlayTest() {
        List<Player> players = playerService.findAll();
        Player player = players.get(0);
        int newBet = (int)player.getBalance() - 1;
        boolean canPlay = false;
        try {
            canPlay = playerService.canPlay(player.getId(), newBet);
        } catch (NoSuchPlayerException e) {
           LOGGER.error(e);
        }
        assertTrue(canPlay);
    }

    @Test
    public void cantPlayTest() {
        List<Player> players = playerService.findAll();
        Player player = players.get(0);
        int newBet = (int)player.getBalance() + 1;
        boolean canPlay = false;
        try {
            canPlay = playerService.canPlay(player.getId(), newBet);
        } catch (NoSuchPlayerException e) {
            LOGGER.error(e);
        }
        assertFalse(canPlay);
    }

    @Test(expected = NoSuchPlayerException.class)
    public void canPlayInvalidUserTest() throws NoSuchPlayerException {
        boolean canPlay = false;
        canPlay = playerService.canPlay(150, BET);
        assertFalse(canPlay);
    }

    @Test
    public void test_Add_Funds() {
        List<Player> players = playerService.findAll();
        Player player = players.get(0);
        double currentBalance = player.getBalance();
        try {
            playerService.addFunds(player.getId(), BET, TransactionType.INCOME);
            Player player1 = playerService.find(player.getId());
            assertEquals(currentBalance + BET, player1.getBalance());
        } catch (NoSuchPlayerException e) {
            LOGGER.error(e);
        }
    }
}
