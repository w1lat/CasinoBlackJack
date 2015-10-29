package vi.talii.dao;

import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.to.Player;

import java.util.List;


public interface PlayerDao {

    Player addNewPlayer(Player player);

    Player getPlayerById(long id) throws NoSuchPlayerException;

    void updatePlayer(Player player);

    List<Player> findAll();

}
