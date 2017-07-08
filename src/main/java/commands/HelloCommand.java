package commands;

import sx.blah.discord.handle.impl.events.guild.channel.message.
        MessageReceivedEvent;
import utils.BotUtils;

/**
 * Greets user
 */
public class HelloCommand implements BotCommand {

    /**
     * Used to call this command via a message
     */
    private static final String COMMAND_NAME = "greet";
    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION = "Greets the user";

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
    public final void runCommand(MessageReceivedEvent event, String args)  {
        String messageToSend = "Hello, " + event.getAuthor().mention();
        BotUtils.sendMessage(event.getChannel(), messageToSend);
    }

    /**
     * Gets command description
     *
     * @return A String with the Command description
     */
    @Override
    public final String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }
}
