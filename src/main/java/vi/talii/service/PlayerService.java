package vi.talii.service;

import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;


public interface PlayerService {

    Player getPlayerById(int id) throws NoSuchPlayerException;
    void updatePlayersCash(int id, float cash);
}
