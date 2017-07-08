package commands;

import sx.blah.discord.handle.impl.events.guild
        .channel.message.MessageReceivedEvent;
import utils.BotUtils;

/**
 * Calculates how much time RexCord has been up
 */
public class UptimeCommand implements BotCommand {

    /**
     * Used to call the command via a message
     */
    private static final String COMMAND_NAME = "uptime";
    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION =
            "Shows how much time the bot has been on";
    /**
     * Amount of hours per day
     */
    private static final int HOURS_PER_DAY = 24;
    /**
     * Amount of minutes per hour
     */
    private static final int MINUTES_PER_HOUR = 60;

    /**
     * Amount of seconds per minute
     */
    private static final int SECONDS_PER_MINUTE = 60;

    /**
     * Amount of milliseconds per second
     */
    private static final int MM_PER_SECOND = 1000;

    /**
     * Gets command name
     * @return command name
     */
    @Override
    public final String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * Runs Uptime Command
     * @param event passed event
     */
    @Override
    public final void runCommand(MessageReceivedEvent event) {
        long runtime = getUptimeInMilliseconds();

        BotUtils.sendMessage(event.getChannel(),
                getFormattedUptime(runtime));
    }

    /**
     * Returns the uptime of the Java virtual machine in milliseconds.
     * @return uptime of the Java virtual machine in milliseconds.
     */
    private long getUptimeInMilliseconds() {
        return System.currentTimeMillis() - BotUtils.getStartTime();
    }

    /**
     * Converts mm to dd:hh:mm format
     * @param uptimeMM mm
     * @return string representation of uptime
     */
    public final String getFormattedUptime(long uptimeMM) {
        long second = (uptimeMM / MM_PER_SECOND) % SECONDS_PER_MINUTE;
        long minute = (uptimeMM / (MM_PER_SECOND * SECONDS_PER_MINUTE))
                % MINUTES_PER_HOUR;
        long hour = (uptimeMM / (MM_PER_SECOND * SECONDS_PER_MINUTE
                * MINUTES_PER_HOUR)) % HOURS_PER_DAY;

        return String.format("RexCord has been sleep deprived for "
                + "%d hours, %d minutes and %d seconds! :sleeping: ",
                (int) hour, (int) minute, second);
    }

    @Override
    public final String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }
}
