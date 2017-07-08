package commands;

import sx.blah.discord.handle.impl.events.guild
        .channel.message.MessageReceivedEvent;

/**
 * All Bot Commands should implement this
 */
public interface BotCommand {

    /**
     * The command name that is used to call the command
     * @return command name
     */
    String getCommandName();
    /**
     * The command description
     * @return A String with the command description
     */
    String getCommandDescription();
    /**
     * Command execution is handled here
     * @param event passed event
     */
    void runCommand(MessageReceivedEvent event);
}
