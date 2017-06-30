package commands;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel
        .message.MessageReceivedEvent;
import utils.BotUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles Commands Execution
 */
public class CommandHandler {

    /**
     * Is called everytime it receives a message
     * @param event event that was received
     */
    @EventSubscriber
    public final void onMessageReceived(MessageReceivedEvent event) {
        String[] received = event.getMessage().getContent().split(" ");

        if (received.length == 0) {
            return;
        }

        if (!received[0].startsWith(BotUtils.BOT_PREFIX)) {
            return;
        }

        String commandString = received[0].substring(2);

        List<String> args = new ArrayList<>(Arrays.asList(received));
        args.remove(0);

        switch (commandString) {
            case "greet":
                new HelloCommand(event);
                break;
            case "about":
                new AboutCommand(event);
                break;
            default:
                return;
        }
    }
}
