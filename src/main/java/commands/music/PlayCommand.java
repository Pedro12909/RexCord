package commands.music;

import commands.BotCommand;
import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel.message
        .MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.audio.AudioPlayer;
import utils.AudioExtractor;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;
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

        //Try to queue song given...if it fails, then try to extract audio
        try {
            queueSong(player, event, args);
        } catch (IOException e) {
            rexCord.sendMessage(event.getChannel(),
                    "Trying to extract audio from link...");

            //Extract audio asyncronously to prevent blocking main thread
            Thread extractor = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        queueSong(player, event, AudioExtractor.extract(args));
                    } catch (IOException e1) {
                        rexCord.sendMessage(event.getChannel(),
                                "Could not retrieve song from URL.");
                    } catch (UnsupportedAudioFileException e1) {
                        rexCord.sendMessage(event.getChannel(),
                            "RexCord does not support that audio file. :(");
                    } catch (NullPointerException e1) {
                        //Extractor returns a null value if it could not
                        //extract the audio from the link
                        rexCord.sendMessage(event.getChannel(),
                                "Could not extract audio.");
                    }
                }
            });
            extractor.start();
        } catch (UnsupportedAudioFileException e) {
            //Audio file given is not supported
            rexCord.sendMessage(event.getChannel(),
                    "RexCord does not support that audio file. :(");
        }

    }

    /**
     * Queues song for the audio player
     * @param player main instance of the AudioPlayer
     * @param event event triggered when command is sent
     * @param audioLink URL linking to audio file
     * @throws IOException thrown if connection could not be made
     * @throws UnsupportedAudioFileException thrown if audio file linked
     *                                       is not playable
     */
    private synchronized void queueSong(AudioPlayer player,
                           MessageReceivedEvent event,
                           String audioLink)
            throws IOException, UnsupportedAudioFileException {
        //Connection to server for music file
        //might be rejected because of no user agent
        URLConnection conn = new URL(audioLink.trim()).openConnection();
        conn.setRequestProperty("User-Agent", rexCord.USER_AGENT);
        InputStream input = conn.getInputStream();
        AudioInputStream audioInputStream
                = AudioSystem.getAudioInputStream(input);

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
    }
}
