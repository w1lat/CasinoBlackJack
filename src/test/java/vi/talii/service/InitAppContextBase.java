package vi.talii.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vi.talii.dao.PlayerDao;
import vi.talii.dao.TransactionDao;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class InitAppContextBase {



    @Mock
    public TransactionDao transactionDao;
    @Mock
    public PlayerDao playerDao;

    protected GameManager gameManager;
    protected DeckService deckService;
    protected PlayerService playerService;


    @Before
    public  void setUpMocks(){

        /*transactionDao = mock(TransactionDao.class);
        playerDao = mock(PlayerDao.class);*/

        try {
            when(playerDao.getPlayerById(1)).thenReturn(new Player(1, 6000));
            when(playerDao.getPlayerById(40)).thenThrow(NoSuchPlayerException.class);
        } catch (NoSuchPlayerException e) {
            e.printStackTrace();
        }

        playerService = new PlayerServiceImpl(playerDao,transactionDao);

        when(playerService.findAll()).thenReturn(Arrays.asList(new Player(1,6000),new Player(2,55555)));

        deckService = new DeckService();

        gameManager = new GameManager(playerService,deckService);



    }


}
