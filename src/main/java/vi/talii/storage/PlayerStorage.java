package vi.talii.storage;

import vi.talii.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vitalii on 06.10.2015.
 */
public class PlayerStorage {

    private Map<Integer, Player> playerStorage;

    public PlayerStorage() {
        init();
    }

    private void init() {
        playerStorage = new HashMap<Integer, Player>();
        playerStorage.put(1, new Player(1, 5000, 0, 0));
        playerStorage.put(2, new Player(2, 6000, 0, 0));
        playerStorage.put(3, new Player(3, 4000, 0, 0));
        playerStorage.put(4, new Player(4, 7000, 0, 0));
    }

    public Player getPlayerById(int id){
        return playerStorage.get(id);
    }

    public void updateCash(int id, int cash){
        Player temp = playerStorage.get(id);
        temp.setCash(cash);
        playerStorage.put(id, temp);
    }
}
