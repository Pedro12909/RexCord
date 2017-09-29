/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.List;

import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel.message.
        MessageReceivedEvent;
import utils.DiscordMarkdown;

/**
 * Informs user about commands description
 */
public class InfoCommand implements BotCommand {

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
    public InfoCommand(RexCord rexCord) {
        this.rexCord = rexCord;
    }

    /**
     * Gets command name
     *
     * @return A String with the Command name
     */
    @Override
    public final String getCommandName() {
        return COMMAND_NAME;
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
        String commandInformation = "All commands information\nPrefix being "
                + "used:" + DiscordMarkdown.bold(rexCord.getBotPrefix());
        List<BotCommand> commands =
                rexCord.getCommandHandler()
                .getAvailableCommands();
        for (int i = 0; i < commands.size(); i++) {
            commandInformation += "\n"
                    + DiscordMarkdown.bold(commands.get(i).getCommandName())
                    + " == "
                    + DiscordMarkdown.bold(commands.get(i)
                    .getCommandDescription());
        }
        rexCord.sendMessage(event.getChannel(), commandInformation);
    }
}
