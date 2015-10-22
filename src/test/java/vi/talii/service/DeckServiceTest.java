package vi.talii.service;

import org.junit.Test;
import vi.talii.model.Card;

import java.util.List;

import static org.junit.Assert.*;


public class DeckServiceTest {

    @Test
    public void getNewNotShuffledDeckTest() throws Exception {
        DeckService deckService = new DeckService();
        List<Card> newDeck = deckService.getNewDeck(false);
        assertEquals(52, newDeck.size());
    }

    @Test
    public void getNewShuffledDeckTest() throws Exception {
        DeckService deckService = new DeckService();
        List<Card> notShuffledDeck = deckService.getNewDeck(false);
        List<Card> shuffledDeck = deckService.getNewDeck(true);
        assertEquals(52, notShuffledDeck.size());
        assertEquals(52, shuffledDeck.size());
        assertNotEquals(notShuffledDeck, shuffledDeck);
    }

    @Test
    public void dealNextCardTest() throws Exception {
        DeckService deckService = new DeckService();
        List<Card> newDeck = deckService.getNewDeck(true);
        Card card = deckService.dealNextCard(newDeck);
        assertNotNull(card);
        assertEquals(51, newDeck.size());
        assertFalse(newDeck.contains(card));
    }
}
