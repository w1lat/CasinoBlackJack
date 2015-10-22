package vi.talii.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;
import vi.talii.storage.PlayerStorage;

import java.util.List;

@Repository
public class StoragePlayerDao{

    @Autowired
    private PlayerStorage playerStorage;

//    public StoragePlayerDao(PlayerStorage playerStorage) {
//        this.playerStorage = playerStorage;
//    }

    public StoragePlayerDao() {
    }

    public Player addNewPlayer(Player player) {
        return null;
    }

    public Player getPlayerById(long id) throws NoSuchPlayerException{

        Player found = playerStorage.getPlayerById(id);
        if(found == null){
            throw new NoSuchPlayerException("Sorry but player with this id does not exist");
        }else return found;
    }

    public List<Player> findAll() {
        return playerStorage.getAll();
    }

    public void updatePlayer(Player player) {
        playerStorage.updatePlayer(player);
    }
}
