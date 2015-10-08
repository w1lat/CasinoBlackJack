package vi.talii.service;

import vi.talii.dao.PlayerDao;
import vi.talii.dao.StoragePlayerDao;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;
import vi.talii.storage.PlayerStorage;


public class PlayerServiceImpl implements PlayerService {

    private PlayerStorage playerStorage = new PlayerStorage();
    private PlayerDao playerDao = new StoragePlayerDao(playerStorage);


    public Player getPlayerById(int id) throws NoSuchPlayerException {
        return playerDao.getPlayerById(id);
    }

    public void updatePlayersCash(int id, float cash) {
        playerDao.updatePlayersCash(id, cash);
    }
}
