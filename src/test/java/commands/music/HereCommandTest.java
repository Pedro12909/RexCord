package commands.music;

import main.RexCord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IVoiceChannel;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RexCord.class)
public class HereCommandTest {

    @InjectMocks
    private HereCommand command;

    @Mock
    private RexCord rexCord;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private MessageReceivedEvent mockEvent;

    @Mock
    private IChannel mockIChannel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void not_null_IVoiceChanel_then_IVoiceChanel_join() throws Exception {

        // arrange
        IVoiceChannel mockIVoiceChannel = mock(IVoiceChannel.class);
        when(mockEvent.getAuthor().getVoiceStateForGuild(mockEvent.getGuild()).getChannel())
                .thenReturn(mockIVoiceChannel);

        // act
        command.runCommand(mockEvent, "");

        // assert
        verify(rexCord, never()).sendMessageWithBlock(any(IChannel.class), anyString());
        verify(mockIVoiceChannel).join();
    }

    @Test
    public void null_IVoiceChanel_then_rexCord_sendMessageWithBlock_with_EventChannel() throws Exception {

        // arrange
        when(mockEvent.getAuthor().getVoiceStateForGuild(mockEvent.getGuild()).getChannel())
                .thenReturn(null);
        when(mockEvent.getChannel()).thenReturn(mockIChannel);

        // act
        command.runCommand(mockEvent, "");

        // assert
        verify(rexCord).sendMessageWithBlock(mockIChannel, "User is not in a voice channel!");
    }

}
