package commands;

import commands.messages.EmbeddedMessage;
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
     * Gets Command description
     * @return command description
     */
    @Override
    public final String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }

    /**
     * Runs command
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        EmbeddedMessage embeddedMessage =
                // title cannot be null/empty
                new EmbeddedMessage("new message from bot",
                        "Hello, " + event.getAuthor().mention());

        rexCord.sendEmbeddedMessage(event.getChannel(), embeddedMessage);
    }
}
