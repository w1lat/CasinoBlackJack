package vi.talii.exception;

import vi.talii.model.GameContext;

public class GameCouldNotBeStartedException extends GameException {

    public GameCouldNotBeStartedException(String message) {
        super(message);
    }
}
