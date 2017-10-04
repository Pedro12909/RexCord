package commands;

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
     * url to image
     */
    private String image;

    /**
     * Creates an embedded message
     * @param title title of the message
     * @param message message content
     * @param image url to image
     */
    public EmbeddedMessage(String title, String message, String image) {
        this.title = title;
        this.message = message;
        this.image = image;
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

    /**
     * gets an image url
     * @return image
     */
    public final String getImage() {
        return image;
    }

}
