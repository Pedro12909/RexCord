package commands.messages;

/**
 * encapsulating for embedded message
 */
public class EmbeddedMessage {

    /**
     * Title
     */
    private String title;
    /**
     * Message
     */
    private String message;

    /**
     * Creates an embedded message
     * @param title title of the message
     * @param message message content
     */
    public EmbeddedMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    /**
     * gets title
     * @return title
     */
    public final String getTitle() {
        return title;
    }

    /**
     * gets a message
     * @return message
     */
    public final String getMessage() {
        return message;
    }

}
