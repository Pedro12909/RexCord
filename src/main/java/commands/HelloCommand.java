package commands;

import sx.blah.discord.handle.impl.events.guild
        .channel.message.MessageReceivedEvent;
import utils.BotUtils;

/**
 * Greets user
 */
public class HelloCommand {

    /**
     * Greets the user that triggered this event
     * @param event received event
     */
    public HelloCommand(final MessageReceivedEvent event) {
        String messageToSend = "Hello, " + event.getAuthor().mention();

        BotUtils.sendMessage(event.getChannel(), messageToSend);
    }
}
