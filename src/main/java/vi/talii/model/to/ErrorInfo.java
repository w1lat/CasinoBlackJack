package vi.talii.model.to;

public class ErrorInfo {

    private final transient String developerMessage;
    private final transient String userMessage;

    public ErrorInfo(String developerMessage, String userMessage) {
        this.developerMessage = developerMessage;
        this.userMessage = userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    // Getters/setters/initializer
}
