package commands.music;

import commands.BotCommand;
import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel.message
        .MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.audio.AudioPlayer;

import java.util.List;

/**
 * Skips current song
 */
public class SkipCommand implements BotCommand {

    /**
     * Instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Command Name
     */
    private static final String COMMAND_NAME = "skip";

    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION
            = "Skips current song";

    /**
     * Creates an instance of the Here Command class
     * @param rexCord main instance of RexCord
     */
    public SkipCommand(RexCord rexCord) {
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
     * Skips song in voice channel
     * @param event passed event
     * @param args passed arguments
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        List<IVoiceChannel> connectedVoiceChannels
                = rexCord.getClient().getConnectedVoiceChannels();
        if (connectedVoiceChannels.size() == 0) {
            rexCord.sendMessage(event.getChannel(),
                                rexCord.NOT_IN_VOICE_CHANNEL);
            return;
        }

        AudioPlayer player
                = AudioPlayer.getAudioPlayerForGuild(event.getGuild());
        if (player.getPlaylistSize() >= 1) {
            player.skip();
            rexCord.sendMessage(event.getChannel(), "Skipped song.");
        } else {
            rexCord.sendMessage(event.getChannel(),
                                "There is nothing to skip.");
        }
    }
}
