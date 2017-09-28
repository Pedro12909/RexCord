package commands.music;

import commands.BotCommand;
import main.RexCord;
import sx.blah.discord.handle.impl.events.guild
        .channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

/**
 * Joins the voice channel of the user who summoned it
 */
public class HereCommand implements BotCommand {

    /**
     * Instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Command Name
     */
    private static final String COMMAND_NAME = "here";

    /**
     * Creates an instance of the Here Command class
     * @param rexCord main instance of RexCord
     */
    public HereCommand(RexCord rexCord) {
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
     * Joins a a voice channel after being summoned
     * @param event passed event
     * @param args passed arguments
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        // The channel the user is in
        IVoiceChannel userChannel = event.getAuthor()
                .getVoiceStateForGuild(event.getGuild()).getChannel();

        if (userChannel == null) {
            rexCord.sendMessageWithBlock(event.getChannel(),
                    "User is not in a voice channel!");
            return;
        }

        userChannel.join();
    }
}
