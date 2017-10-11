package utils;

import main.RexCord;
import sx.blah.discord.handle.obj.IUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Stores and sends reminders to user
 */
public class RemindHandler {
    /**
     * Running list of reminders
     */
    private ArrayList<Reminder> reminders;

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Create instance of RemindHandler
     * @param rexCord main instance of RexCord
     */
    public RemindHandler(RexCord rexCord) {
        reminders = new ArrayList<>();
        this.rexCord = rexCord;
    }

    /**
     * Adds a reminder to the running list of reminders
     * @param reminder reminder
     */
    public final synchronized void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    /**
     * Dispatches all reminders once the target time is hit
     */
    public final synchronized void dispatchReminders() {
        Iterator<Reminder> iterator = reminders.iterator();

        while (iterator.hasNext()) {
            Reminder reminder = iterator.next();

            Date targetDate = reminder.getTargetDate();

            if (targetDate.getTime() >= System.currentTimeMillis()) {
                continue;
            } else {
                IUser recipient = reminder.getRecipient();
                String message = reminder.getMessage();

                String content = String.format("Here is your reminder: \"%s\"",
                                                message);
                rexCord.sendMessageToUser(recipient, content);
                iterator.remove();
            }
        }
    }
}
