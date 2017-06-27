package commands;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import utils.BotUtils;

/**
 * Greets user
 */
public class HelloCommand {

    public HelloCommand(MessageReceivedEvent event) {
        String messageToSend = "Hello, " + event.getAuthor().mention();

        BotUtils.sendMessage(event.getChannel(), messageToSend);
    }
}
