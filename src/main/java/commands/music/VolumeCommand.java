package commands.music;

import commands.BotCommand;
import sx.blah.discord.handle.impl.events.guild
        .channel.message.MessageReceivedEvent;
import main.RexCord;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.audio.AudioPlayer;

import java.util.List;

/**
 * Changes the volume of audio being played
 */
public class VolumeCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Used to call the command via a message
     */
    private static final String COMMAND_NAME = "volume";
    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION =
            "Changes volume(0-100) of the audio player";

    /**
     * Creates an instance of the Uptime Command class
     * @param rexCord main instance of RexCord
     */
    public VolumeCommand(RexCord rexCord) {
        this.rexCord = rexCord;
    }

    /**
     * Gets command name
     * @return command name
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
     * Runs Volume Command
     * @param event passed event
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

        final float maxVolume = 100f;
        final float minVolume = 0.0f;
        try {
            float volume = Float.parseFloat(args.trim());
            //Keep volume within 0-100% range
            volume = Math.min(maxVolume, volume);
            volume = Math.max(minVolume, volume);

            rexCord.sendMessage(event.getChannel(),
                    String.format("Volume is now set to %.1f%%", volume));
            //Scale down percentage to value in between 0 and 1.0
            volume /= maxVolume;
            player.setVolume(volume);
        } catch (NumberFormatException e) {
            if (args.trim().length() == 0) {
                rexCord.sendMessage(event.getChannel(),
                        String.format("The current volume is at %.1f%%",
                            player.getVolume() * maxVolume));
            } else {
                rexCord.sendMessage(event.getChannel(),
                        "Could not set volume.");
            }
        }
    }
}
