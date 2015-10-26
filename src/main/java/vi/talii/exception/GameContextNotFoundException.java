package vi.talii.exception;

public class GameContextNotFoundException extends GameException {

    public GameContextNotFoundException() {
        super("Could not execute command, game context not found");
    }

}
