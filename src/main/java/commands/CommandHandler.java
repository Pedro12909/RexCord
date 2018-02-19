package commands;

import main.RexCord;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.MissingPermissionsException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles Commands Execution
 */
public class CommandHandler {

    /**
     * Instance of RexCord
     */
    private RexCord rexCord;

    /**
     * HashMap containing all commands supported by RexCord
     * Each key is what invokes it's corresponding command
     */
    private Map<String, BotCommand> commands = new HashMap();

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

        populateCommands();
    }

    /**
     * All supported commands should be in the commands map
     */
    private void populateCommands() {
        commands.put("greet", new HelloCommand(rexCord));
        commands.put("uptime", new UptimeCommand(rexCord));
        commands.put("about", new AboutCommand(rexCord));
        commands.put("shutdown", new ShutdownCommand(rexCord));
        commands.put("math", new MathCommand(rexCord));
        commands.put("help", new HelpCommand(rexCord));
        commands.put("gif", new GifCommand(rexCord));
        commands.put("remindme", new RemindCommand(rexCord));
    }

    /**
     * Getter method for available commands
     * @return map of all available commands
     */
    public final Map<String, BotCommand> getCommands() {
        return commands;
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

        if (commands.containsKey(commandString)
                && textChannelIsSetAsListen(event.getChannel())) {
            BotCommand cmd = commands.get(commandString);

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
