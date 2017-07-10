package commands;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message
        .MessageReceivedEvent;
import utils.BotUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles Commands Execution
 */
public class CommandHandler {

    /**
     * A list of all available commands
     */
    private static List<BotCommand> availableCommands
            = new ArrayList<>(Arrays.asList(
                    new HelloCommand(),
                    new UptimeCommand(),
                    new AboutCommand(),
                    new InfoCommand(),
                    new ShutdownCommand(),
                    new RequestCommand()
            ));
    /**
     * Returns all available commands
     * @return A List with all the available commands
     */
    public static List<BotCommand> getAvailableCommands() {
        return availableCommands;
    }
    /**
     * Is called everytime it receives a message
     *
     * @param event event that was received
     */
    @EventSubscriber
    public final void onMessageReceived(MessageReceivedEvent event) {
        String[] received = event.getMessage().getContent().split(" ");

        if (received.length == 0) {
            return;
        }

        if (!received[0].startsWith(BotUtils.getBotPrefix())) {
            return;
        }

        int numberOfCharsInPrefix = BotUtils.getBotPrefix().length();

        // Creates a substring of the received message without the bot's prefix
        String commandString = received[0].substring(numberOfCharsInPrefix);

        List<String> args = new ArrayList<>(Arrays.asList(received));
        args.remove(0);

        // Iterates through all available commands
        for (BotCommand cmd : availableCommands) {
            // If given command name is a valid command name, executes it
            if (commandString.equals(cmd.getCommandName())
                    && !BannedCommands.isCommandBanned(commandString)) {
                cmd.runCommand(event, String.join(" ", args));
            }
        }
    }
}
