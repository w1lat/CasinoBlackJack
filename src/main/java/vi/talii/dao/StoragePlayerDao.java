package vi.talii.dao;

import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;
import vi.talii.storage.PlayerStorage;


public class StoragePlayerDao implements PlayerDao {

    private PlayerStorage playerStorage;

    public StoragePlayerDao(PlayerStorage playerStorage) {
        this.playerStorage = playerStorage;
    }

    public Player addNewPlayer(Player player) {
        return null;
    }

    public Player getPlayerById(int id) throws NoSuchPlayerException{

        Player found = playerStorage.getPlayerById(id);
        if(found == null){
            throw new NoSuchPlayerException("Sorry but player with this id does not exist");
        }else return found;
    }

    public void updatePlayersCash(int id, float cash) {
        playerStorage.updateCash(id, cash);
    }
}
