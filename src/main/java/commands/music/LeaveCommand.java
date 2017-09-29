package commands.music;

import commands.BotCommand;
import main.RexCord;
import sx.blah.discord.handle.impl.events.guild
        .channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

/**
 * Leaves current voice channel
 */
public class LeaveCommand implements BotCommand {

    /**
     * Instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Command Name
     */
    private static final String COMMAND_NAME = "leave";

    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION
            = "Makes RexCord leave current channel";

    /**
     * Creates an instance of Leave Command Class
     * @param rexCord main instance of RexCord
     */
    public LeaveCommand(RexCord rexCord) {
        this.rexCord = rexCord;
    }

    /**
     * Gets command name
     * @return Command name
     */
    @Override
    public final String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * Gets Command description
     * @return command description
     */
    @Override
    public final String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }

    /**
     * Leaves current voice channel
     * @param event passed event
     * @param args passed arguments
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        // The channel the bot is in
        IVoiceChannel currentChannel = event.getClient().getOurUser()
                .getVoiceStateForGuild(event.getGuild()).getChannel();

        if (currentChannel == null) {
            rexCord.sendMessageWithBlock(event.getChannel(),
                    "RexCord is not in a voice channel!");
            return;
        }

        currentChannel.leave();
    }
}
