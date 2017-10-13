package commands.music;

import main.RexCord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.audio.AudioPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RexCord.class, AudioPlayer.class})
public class PauseCommandTest {

    @InjectMocks
    private PauseCommand command;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private RexCord rexCord;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private MessageReceivedEvent mockEvent;

    @Mock
    private IGuild mockIGuild;

    @Mock
    private IChannel mockIChannel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<IVoiceChannel> voiceChannels = new ArrayList<>();
        voiceChannels.add(mock(IVoiceChannel.class));
        when(rexCord.getClient().getConnectedVoiceChannels()).thenReturn(voiceChannels);


        when(mockEvent.getChannel()).thenReturn(mockIChannel);
    }

    @Test
    public void empty_IVoiceChannels_then_rexCord_sendMessage_NotInAVoiceChannel() throws Exception {

        // arrange
        when(rexCord.getClient().getConnectedVoiceChannels()).thenReturn(Collections.EMPTY_LIST);

        // act
        command.runCommand(mockEvent, "");

        // assert
        verify(rexCord).sendMessage(mockIChannel, RexCord.NOT_IN_VOICE_CHANNEL);
    }

    @Test
    public void playlistSize_less_than_one_then_rexCord_sendMessage_NotASongToPause() throws Exception {

        // arrange on setUp

        // act
        command.runCommand(mockEvent, "");

        // assert
        verify(rexCord).sendMessage(mockIChannel, "There is not a song to pause.");
    }

    @Test
    public void playlistSize_more_than_zero_and_unpaused_then_rexCord_sendMessage_UnpausedSong() throws Exception {

        // arrange on setUp
        AudioPlayer mockAudioPlayer = mock(AudioPlayer.class);
        when(mockAudioPlayer.getPlaylistSize()).thenReturn(1);
        when(mockAudioPlayer.isPaused()).thenReturn(false);

        PowerMockito.mockStatic(AudioPlayer.class);
        when(AudioPlayer.getAudioPlayerForGuild(mockEvent.getGuild())).thenReturn(mockAudioPlayer);

        // act
        command.runCommand(mockEvent, "");

        // assert
        verify(mockAudioPlayer).togglePause();
        verify(rexCord).sendMessage(mockIChannel, "Unpaused song.");
    }

    @Test
    public void playlistSize_more_than_zero_and_paused_then_rexCord_sendMessage_PausedSong() throws Exception {

        // arrange on setUp
        AudioPlayer mockAudioPlayer = mock(AudioPlayer.class);
        when(mockAudioPlayer.getPlaylistSize()).thenReturn(1);
        when(mockAudioPlayer.isPaused()).thenReturn(true);

        PowerMockito.mockStatic(AudioPlayer.class);
        when(AudioPlayer.getAudioPlayerForGuild(mockEvent.getGuild())).thenReturn(mockAudioPlayer);

        // act
        command.runCommand(mockEvent, "");

        // assert
        verify(mockAudioPlayer).togglePause();
        verify(rexCord).sendMessage(mockIChannel, "Paused song.");
    }

}
