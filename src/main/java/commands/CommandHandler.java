package commands;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message
        .MessageReceivedEvent;
import main.RexCord;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.MissingPermissionsException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles Commands Execution
 */
public class CommandHandler {

    /**
     * Instance of RexCord
     */
    private RexCord rexCord;

    /**
     * A list of all available commands
     */
    private final List<BotCommand> availableCommands;

    /**
     * When RexCord has no permission to post in a text channel
     */
    private static final String NO_PERM_ERROR =
            "Sorry, but I don't have permission to post there "
                    + ":zipper_mouth:";

    /**
     * Creates an instance of CommandHandler
     * @param rexCord main instance of RexCord
     */
    public CommandHandler(RexCord rexCord) {
        this.rexCord = rexCord;
        availableCommands = new ArrayList<>(Arrays.asList(
                new HelloCommand(rexCord),
                new UptimeCommand(rexCord),
                new AboutCommand(rexCord),
                new ShutdownCommand(rexCord),
                new MathCommand(rexCord),
                new InfoCommand(rexCord),
                new RequestCommand(rexCord),
                new JokeCommand(rexCord),
                new GifCommand(rexCord)
        ));
    }

    /**
     * Returns all available commands
     * @return A List with all the available commands
     */
    public final List<BotCommand> getAvailableCommands() {
        return availableCommands;
    }

    /**
     * Is called every time it receives a message
     *
     * @param event event that was received
     */
    @EventSubscriber
    public final void onMessageReceived(MessageReceivedEvent event) {
        String[] received = event.getMessage().getContent().split(" ");

        if (received.length == 0) {
            return;
        }

        if (!received[0].startsWith(rexCord.getBotPrefix())) {
            return;
        }

        int numberOfCharsInPrefix = rexCord.getBotPrefix().length();

        // Creates a substring of the received message without the bot's prefix
        String commandString = received[0].substring(numberOfCharsInPrefix);

        String[] args = Arrays.copyOfRange(received, 1, received.length);

        // Iterates through all available commands
        for (BotCommand cmd : availableCommands) {
            // If given command name is a valid command name, executes it
            if (commandString.equals(cmd.getCommandName())
                    && !rexCord.getBannedCommands()
                    .isCommandBanned(commandString)
                    && textChannelIsSetAsListen(event.getChannel())) {
                try {
                    if (rexCord.getPermissions().
                                isAllowed(event, commandString)) {
                        cmd.runCommand(event, String.join(" ", args));
                    } else {
                        rexCord.sendMessage(event.getChannel(),
                                                rexCord.PERMISSION_ERROR);
                    }
                } catch (MissingPermissionsException e) {
                    event.getAuthor()
                            .getOrCreatePMChannel()
                            .sendMessage(NO_PERM_ERROR);
                }
            }
        }
    }

    /**
     * Checks if RexCord is listening to given text channel
     * @param channel given channel
     * @return true if is listening
     */
    private boolean textChannelIsSetAsListen(IChannel channel) {
        for (long id : rexCord.getListenChannels()) {
            IChannel c = rexCord.getClient().getChannelByID(id);
            if (channel.equals(c)) {
                return true;
            }
        }

        return false;
    }
}
