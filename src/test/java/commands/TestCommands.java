package commands;

import main.RexCord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {RexCord.class, CommandHandler.class})
public class TestCommands {

    @Mock
    private RexCord rexCord;

    @Mock
    private MessageReceivedEvent event;

    @Mock
    private IChannel channel;

    @Mock
    private IMessage message;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(event.getChannel()).thenReturn(channel);
        when(channel.getName()).thenReturn("channel-test");
        when(event.getMessage()).thenReturn(message);
    }

    @Test
    public void testMathCommandInvalidExpression() {
        MathCommand command = new MathCommand(rexCord);
        final String input = "not-a-valid-expression";
        command.runCommand(event, input);
        verify(rexCord, times(1)).sendMessage(channel, String.format("Expression is invalid!\nExpression:%s", input));
    }

    @Test
    public void testMathCommandExpression() {
        MathCommand command = new MathCommand(rexCord);
        final String input = "1+1";
        command.runCommand(event, input);
        verify(rexCord, times(1)).sendMessage(eq(channel), contains("1+1=2"));
    }

    @Test
    public void testAboutCommand() {
        AboutCommand command = new AboutCommand(rexCord);
        command.runCommand(event, "");
        verify(rexCord, times(1)).sendEmbeddedMessage(eq(channel), anyObject());
    }

    @Test
    public void testHelloCommand() {
        HelloCommand command = new HelloCommand(rexCord);
        IUser user = mock(IUser.class);
        final String username = "test-user";
        when(event.getAuthor()).thenReturn(user);
        when(event.getAuthor().mention()).thenReturn(username);
        command.runCommand(event, "");
        verify(rexCord, times(1)).sendMessage(eq(channel), contains("test-user"));
    }

    @Test
    public void testRemindCommandFail() {
        RemindCommand command = new RemindCommand(rexCord);
        command.runCommand(event, "do not remind me anything!!");
        verify(rexCord, times(1)).sendMessage(eq(channel), eq("Message not detected"));
    }

    @Test
    public void testRemindCommandQueryFail() {
        RemindCommand command = new RemindCommand(rexCord);
        command.runCommand(event, "\"blabla\"");
        verify(rexCord, times(1)).sendMessage(eq(channel), eq("Could not interpret query :("));
    }

    @Test
    public void testRemindCommand() {
        RemindCommand command = new RemindCommand(rexCord);
        command.runCommand(event, "\"April 20 at 10am\"");
        verify(rexCord, times(1)).sendMessage(eq(channel), contains("Ok, I'll remind you"));
    }

    @Test
    public void testUptimeCommand() {
        UptimeCommand command = new UptimeCommand(rexCord);
        command.runCommand(event, "");
        verify(rexCord, times(1)).sendMessage(eq(channel), contains("RexCord has been sleep deprived for "));
    }

}
