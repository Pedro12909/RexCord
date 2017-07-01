package commands;

import sx.blah.discord.handle.impl.events.guild
        .channel.message.MessageReceivedEvent;
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
     * Gets command name
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
    public final void runCommand(MessageReceivedEvent event)  {
        String messageToSend = "Hello, " + event.getAuthor().mention();

        BotUtils.sendMessage(event.getChannel(), messageToSend);
    }
}
