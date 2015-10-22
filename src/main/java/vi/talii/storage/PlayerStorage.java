package vi.talii.storage;

import org.springframework.stereotype.Repository;
import vi.talii.model.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
public class PlayerStorage {

    private Map<Long, Player> playerStorage;

    public PlayerStorage() {
        init();
    }

    private void init() {
        playerStorage = new HashMap<Long, Player>();
        playerStorage.put(1L, new Player(1, 5000));
        playerStorage.put(2L, new Player(2, 6000));
        playerStorage.put(3L, new Player(3, 4000));
        playerStorage.put(4L, new Player(4, 7000));
    }

    public Player getPlayerById(long id){
        return playerStorage.get(id);
    }

    public void updatePlayer(Player player){
        playerStorage.put(player.getId(), player);
    }

    public List<Player> getAll() {
        return (List<Player>) playerStorage.values();
    }
}
