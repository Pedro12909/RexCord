package utils;

import sx.blah.discord.handle.obj.IUser;

import java.util.Date;

/**
 * Reminder Structure
 */
public class Reminder {

    /**
     * When the reminder will be sent
     */
    private Date targetDate;

    /**
     * Reminder message
     */
    private String message;

    /**
     * Whom to send the message to
     */
    private IUser recipient;

    /**
     * Creates instance of Reminder
     * @param targetDate When the reminder will be sent
     * @param message Reminder message
     * @param recipient Whom to send the messageto
     */
    public Reminder(Date targetDate, String message, IUser recipient) {
        this.targetDate = targetDate;
        this.message = message;
        this.recipient = recipient;
    }

    /**
     * Get the date the reminder will be sent on
     * @return target date
     */
    public final Date getTargetDate() {
        return targetDate;
    }

    /**
     * Get the message of the reminder
     * @return message
     */
    public final String getMessage() {
        return message;
    }

    /**
     * Get the recipient of the reminder
     * @return recipient
     */
    public final IUser getRecipient() {
        return recipient;
    }

}
