package vi.talii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vi.talii.dao.PlayerDao;
import vi.talii.dao.StoragePlayerDao;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {


    @Qualifier("hibernatePlayerDao")
    @Autowired
    private PlayerDao playerDao;

    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public PlayerServiceImpl() {
    }

    public Player getPlayerById(int id) throws NoSuchPlayerException {
        return playerDao.getPlayerById(id);
    }

    public void updatePlayersCash(int id, double cash) {
        playerDao.updatePlayersCash(id, cash);
    }

    public List<Player> getAll(){
        return playerDao.showAll();
    }
}
