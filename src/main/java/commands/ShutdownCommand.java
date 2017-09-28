package commands;

import sx.blah.discord.handle.impl.events.guild
        .channel.message.MessageReceivedEvent;
import main.RexCord;

/**
 * Logs out and terminates application
 */
public class ShutdownCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Used to call this command via a message
     */
    private static final String COMMAND_NAME = "shutdown";
    /**
     * Command description
     */
    private static final String COMMAND_DESCRIPTION = "Shutdowns bot";

    /**
     * Creates an instance of Shutdown Command class
     * @param rexCord main instance of RexCord
     */
    public ShutdownCommand(RexCord rexCord) {
        this.rexCord = rexCord;
    }

    /**
     * Returns command name
     * @return Command Name
     */
    @Override
    public final String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * Runs this command
     * @param event passed event
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        rexCord.sendMessage(event.getChannel(),
                "Disconnecting! :wave:");
        rexCord.getClient().logout();
        System.exit(0);
    }
}
