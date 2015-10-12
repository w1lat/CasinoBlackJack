package vi.talii.service;

import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;

import java.util.List;


public interface PlayerService {

    Player getPlayerById(int id) throws NoSuchPlayerException;
    void updatePlayersCash(int id, double cash);
    List<Player> getAll();
}
