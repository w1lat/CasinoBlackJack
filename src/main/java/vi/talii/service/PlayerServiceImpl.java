package vi.talii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vi.talii.dao.PlayerDao;
import vi.talii.dao.StoragePlayerDao;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;

@Service
public class PlayerServiceImpl implements PlayerService {


    @Autowired
    private PlayerDao playerDao = new StoragePlayerDao();

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
}
