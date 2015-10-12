package vi.talii.dao;

import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;

import java.util.List;


public interface PlayerDao {

    Player addNewPlayer(Player player);
    Player getPlayerById(int id) throws NoSuchPlayerException;
    void updatePlayersCash(int id, double cash);
    List<Player> showAll();
}
