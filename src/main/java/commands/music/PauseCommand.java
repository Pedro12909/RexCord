package commands.music;

import commands.BotCommand;
import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel.message
        .MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.audio.AudioPlayer;

import java.util.List;

/**
 * Pauses RexCord if it is currently playing a song
 */
public class PauseCommand implements BotCommand {

    /**
     * Instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Command Name
     */
    private static final String COMMAND_NAME = "pause";

    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION
            = "Pauses current song";

    /**
     * Creates an instance of the Here Command class
     * @param rexCord main instance of RexCord
     */
    public PauseCommand(RexCord rexCord) {
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
     * Pauses song in voice channel
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
            player.togglePause();

            //Sends corresponding message after pause state has changed
            if (player.isPaused()) {
                rexCord.sendMessage(event.getChannel(), "Paused song.");
            } else {
                rexCord.sendMessage(event.getChannel(), "Unpaused song.");
            }
        } else {
            rexCord.sendMessage(event.getChannel(),
                                "There is not a song to pause.");
        }
    }
}
