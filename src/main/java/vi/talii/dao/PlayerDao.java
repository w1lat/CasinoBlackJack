package vi.talii.dao;

import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;


public interface PlayerDao {

    Player addNewPlayer(Player player);
    Player getPlayerById(int id) throws NoSuchPlayerException;
    void updatePlayersCash(int id, double cash);
}
