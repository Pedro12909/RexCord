package commands.music;

import commands.BotCommand;
import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel.message
        .MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Plays a given song
 */
public class PlayCommand implements BotCommand {

    /**
     * Instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Command Name
     */
    private static final String COMMAND_NAME = "play";

    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION
            = "Plays music from URL. NOTE: URL must link to an audio file "
            + "thus you might need to use a video to mp3 converter.";

    /**
     * Creates an instance of the Here Command class
     * @param rexCord main instance of RexCord
     */
    public PlayCommand(RexCord rexCord) {
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
     * Plays music in voice channel
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

        //Instead of using the pause command,
        //user may instead use the play command
        if (player.isPaused() && args.trim().isEmpty()) {
            player.togglePause();
            rexCord.sendMessage(event.getChannel(), "Unpaused song.");
            return;
        }

        try {
            //Connection to server for music file
            //might be rejected because of no user agent
            URLConnection conn = new URL(args.trim()).openConnection();
            conn.setRequestProperty("User-Agent", rexCord.USER_AGENT);
            AudioInputStream audioInputStream
                    = AudioSystem.getAudioInputStream(conn.getInputStream());

            player.queue(audioInputStream);

            String message
                    = String.format(
                        "Song is now queued! Your song is #%d on the queue.",
                            player.getPlaylistSize());
            rexCord.sendMessage(event.getChannel(), message);


            //Start playing music if there is nothing in the playlist.
            if (player.getPlaylistSize() == 0) {
                player.provide();
            }
        } catch (IOException e) {
            rexCord.sendMessage(event.getChannel(),
                    "Could not retrieve song from URL.");
        } catch (UnsupportedAudioFileException e) {
            rexCord.sendMessage(event.getChannel(),
                    "RexCord does not support that audio file. :(");
        }

    }
}
