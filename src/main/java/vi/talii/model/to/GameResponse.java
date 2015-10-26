package vi.talii.model.to;

import vi.talii.model.Card;
import vi.talii.model.GameResult;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class GameResponse implements Serializable {

    private String id;

    private int bet;

    private int playersScore;

    private List<Card> playerCards;

    private int dealersScore;

    private List<Card> dealersCards;

    private GameResult gameResult;

    public GameResponse(String id, List<Card> playerCards, List<Card> dealersCards, int playersScore, int dealersScore,
                        int bet) {
        this.id = id;
        this.playerCards = playerCards;
        this.dealersCards = dealersCards;
        this.playersScore = playersScore;
        this.dealersScore = dealersScore;
        this.bet = bet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public int getPlayersScore() {
        return playersScore;
    }

    public void setPlayersScore(int playersScore) {
        this.playersScore = playersScore;
    }

    public List<Card> getDealersCards() {
        return dealersCards;
    }

    public void setDealersCards(List<Card> dealersCards) {
        this.dealersCards = dealersCards;
    }

    public int getDealersScore() {
        return dealersScore;
    }

    public void setDealersScore(int dealersScore) {
        this.dealersScore = dealersScore;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameResponce{");
        sb.append("id='").append(id).append('\'');
        sb.append(", bet=").append(bet);
        sb.append(", playersScore=").append(playersScore);
        sb.append(", playerCards=").append(playerCards);
        sb.append(", dealersScore=").append(dealersScore);
        sb.append(", dealersCards=").append(dealersCards);
        sb.append(", gameResult=").append(gameResult);
        sb.append('}');
        return sb.toString();
    }
}
