package vi.talii.service;

import vi.talii.model.Card;

import java.util.List;


public interface SetService {

    List<Card> shuffle();
    Card getTopCard();
}
