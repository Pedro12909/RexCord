package commands;

import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel
        .message.MessageReceivedEvent;

/**
 * Greets user
 */
public class HelloCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

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
        String messageToSend = "Hello, " + event.getAuthor().mention();

        rexCord.sendMessage(event.getChannel(), messageToSend);
    }
}
