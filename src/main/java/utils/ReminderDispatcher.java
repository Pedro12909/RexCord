package utils;

/**
 * Background thread that dispatches the reminders to users
 */
public class ReminderDispatcher implements Runnable {

    /**
     * RemindHandler instance
     */
    private RemindHandler remindHandler;

    /**
     * Create instance of ReminderDispatcher
     * @param remindHandler Main instance of ReminderDispatcher
     */
    public ReminderDispatcher(RemindHandler remindHandler) {
        this.remindHandler = remindHandler;
    }

    /**
     * Runs the dispatcher
     */
    @Override
    public final void run() {
        while (true) {
            remindHandler.dispatchReminders();
        }
    }
}
