package commands;

import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import utils.DiscordMarkdown;

import java.util.Map;

/**
 * Informs user about commands description
 */
public class HelpCommand implements BotCommand {

    /**
     * Instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Represents the command name
     */
    private static final String COMMAND_NAME = "info";

    /**
     * Command description
     */
    private static final String COMMAND_DESCRIPTION =
            "Displays information about all available commands";

    /**
     * Creates an instance of the Info Command Class
     * @param rexCord main instance of RexCord
     */
    public HelpCommand(RexCord rexCord) {
        this.rexCord = rexCord;
    }

    /**
     * Gets command description
     * @return command description
     */
    public final String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }

    /**
     * Runs the command triggered by the user
     *
     * @param event the event triggered by the user
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        String commandInformation = "All commands information\n"
                + "Prefix being " + "used:"
                + DiscordMarkdown.bold(rexCord.getBotPrefix());

        for (Map.Entry<String, BotCommand> entry
                : rexCord.getCommandHandler().getCommands().entrySet()) {
            String commandName = entry.getKey();
            BotCommand command = entry.getValue();
            commandInformation += "\n"
                    + DiscordMarkdown.bold(commandName)
                    + " == "
                    + DiscordMarkdown.bold(command
                    .getCommandDescription());
        }

        EmbeddedMessage embeddedMessage =
                new EmbeddedMessage("Commands",
                        commandInformation, "");

        rexCord.sendEmbeddedMessage(event.getChannel(), embeddedMessage);
    }
}
