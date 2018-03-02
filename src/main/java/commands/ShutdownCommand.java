package commands;

import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel
        .message.MessageReceivedEvent;

/**
 * Logs out and terminates application
 */
public class ShutdownCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Command description
     */
    private static final String COMMAND_DESCRIPTION
            = "Shuts down RexCord";

    /**
     * Creates an instance of Shutdown Command class
     * @param rexCord main instance of RexCord
     */
    public ShutdownCommand(RexCord rexCord) {
        this.rexCord = rexCord;
    }

    /**
     * Gets Command description
     * @return command description
     */
    @Override
    public final String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }

    /**
     * Runs this command
     * @param event passed event
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        rexCord.sendMessage(event.getChannel(),
                ":wave: Disconnecting!");
        rexCord.getClient().logout();
        System.exit(0);
    }
}
