package commands;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * All Bot Commands should implement this
 */
public interface BotCommand {

    /**
     * A brief description of the command
     * @return description of the command
     */
    String getCommandDescription();

    /**
     * Command execution is handled here
     * @param event passed event
     * @param args passed arguments
     */
    void runCommand(MessageReceivedEvent event, String args);
}
