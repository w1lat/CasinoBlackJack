package vi.talii.dao;

import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;

/**
 * Created by Vitalii on 06.10.2015.
 */
public interface PlayerDao {

    Player addNewPlayer(Player player);
    Player getPlayerById(int id) throws NoSuchPlayerException;
    void updatePlayersCash(int id, int cash);
}
