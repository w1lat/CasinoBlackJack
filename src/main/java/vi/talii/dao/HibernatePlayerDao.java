package vi.talii.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vi.talii.exception.NoSuchPlayerException;
import vi.talii.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class HibernatePlayerDao implements PlayerDao {

    private static final Logger LOG = Logger.getLogger(HibernatePlayerDao.class);

    @Autowired
    private EntityManagerFactory factory;

    public HibernatePlayerDao() {
    }

    public HibernatePlayerDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Player addNewPlayer(Player player) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin(); //
            entityManager.persist(player);
            transaction.commit();//
            return player;
        }catch (Exception e){
            LOG.error(e);
            transaction.rollback();
        }
        entityManager.close();
        return null;
    }

    public Player getPlayerById(int id) throws NoSuchPlayerException {
        EntityManager entityManager = factory.createEntityManager();
        Player find = entityManager.find(Player.class,id);
        if(find == null) throw new NoSuchPlayerException("Sorry but player with this id does not exist");
        return find;
    }

    public void updatePlayersCash(int id, double cash) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            Player player = getPlayerById(id);
            player.setCash(cash);
            entityManager.merge(player);
            transaction.commit();//implicitly flushes if flush mode is COMMIT or AUTO.
        } catch (NoSuchPlayerException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Player> showAll(){
        EntityManager entityManager = factory.createEntityManager();
        TypedQuery<Player> query = entityManager.createQuery("SELECT p FROM Player p", Player.class);
        List<Player> all = query.getResultList();
        return all;
    }
}
