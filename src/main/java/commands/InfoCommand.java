/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.List;
import sx.blah.discord.handle.impl.events.guild.channel.message.
        MessageReceivedEvent;
import utils.BotUtils;
import utils.DiscordMarkdown;

/**
 * Informs user about commands description
 */
public class InfoCommand implements BotCommand {

    /**
     * Represents the command name
     */
    private static final String COMMAND_NAME = "info";
    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION
            = "Shows all bot commands information";

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
     * Runs the command triggered by the user
     *
     * @param event the event triggered by the user
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        String commandInformation = "All commands information\nPrefix being "
                + "used:" + DiscordMarkdown.bold(BotUtils.getBotPrefix());
        List<BotCommand> commands = CommandHandler.getAvailableCommands();
        for (int i = 0; i < commands.size(); i++) {
            commandInformation += "\n"
                    + DiscordMarkdown.bold(commands.get(i).getCommandName())
                    + ":"
                    + DiscordMarkdown.italic(commands.get(i).
                            getCommandDescription());
        }
        BotUtils.sendMessage(event.getChannel(), commandInformation);
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
