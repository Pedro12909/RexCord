package commands;

import main.RexCord;
import sx.blah.discord.handle.impl.events
        .guild.channel.message.MessageReceivedEvent;

/**
 * Greets user
 */
public class HelloCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Used to call this command via a message
     */
    private static final String COMMAND_NAME = "greet";

    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION = "Greets the user";

    /**
     * Creates an instance of the Hello Command Class
     * @param rexCord main instance of RexCord
     */
    public HelloCommand(RexCord rexCord) {
        this.rexCord = rexCord;
    }

    /**
     * Gets command name
     *
     * @return command name
     */
    @Override
    public final String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * Runs command
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        String messageToSend = "Hello, " + event.getAuthor().mention();

        rexCord.sendMessage(event.getChannel(), messageToSend);
    }
}
