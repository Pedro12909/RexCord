package main;

import commands.EmbeddedMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IPrivateChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.RequestBuffer;
import test.AnswerWithSelf;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RexCord.class, MessageBuilder.class, RequestBuffer.class})
public class RexCordTest {

    @InjectMocks
    private RexCord rexCord;

    @Mock
    private IChannel mockIChannel;

    @Mock
    private IUser mockIUser;

    @Mock
    private IDiscordClient mockIDiscordClient;

    @Mock
    private IPrivateChannel mockIPrivateChannel;

    @Captor
    private ArgumentCaptor<RequestBuffer.IVoidRequest> iVoidRequestCaptor;

    private String title = "simple title";
    private String message = "simple message";
    private String urlToImage = "http://localhost/image.png";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sendMessage() throws Exception {

        // act
        rexCord.sendMessage(mockIChannel, message);

        // arrest
        verify(mockIChannel).sendMessage(message);
    }

    @Test
    public void sendMessageWithBlock() throws Exception {

        // act
        rexCord.sendMessageWithBlock(mockIChannel, message);

        // arrest
        verify(mockIChannel).sendMessage("`" + message + "`");
    }

    @Test
    public void sendMessageToUser() throws Exception {

        // arrange
        when(mockIDiscordClient.getOrCreatePMChannel(mockIUser)).thenReturn(mockIPrivateChannel);

        MessageBuilder mockMessageBuilder = mock(MessageBuilder.class, new AnswerWithSelf(MessageBuilder.class));
        PowerMockito.whenNew(MessageBuilder.class).withArguments(mockIDiscordClient).thenReturn(mockMessageBuilder);

        // act
        rexCord.sendMessageToUser(mockIUser, message);

        // arrest
        PowerMockito.verifyNew(MessageBuilder.class).withArguments(mockIDiscordClient);
        verify(mockMessageBuilder).withChannel(mockIPrivateChannel);
        verify(mockMessageBuilder).appendContent(message);
        verify(mockMessageBuilder).send();
    }

    @Test
    public void sendEmbeddedMessage() throws Exception {

        // arrange
        EmbedBuilder mockEmbedBuilder = mock(EmbedBuilder.class, new AnswerWithSelf(EmbedBuilder.class));
        PowerMockito.whenNew(EmbedBuilder.class).withNoArguments().thenReturn(mockEmbedBuilder);

        EmbedObject mockEmbedObject = mock(EmbedObject.class);
        PowerMockito.when(mockEmbedBuilder.build()).thenReturn(mockEmbedObject);

        PowerMockito.mockStatic(RequestBuffer.class);

        // act
        rexCord.sendEmbeddedMessage(mockIChannel, new EmbeddedMessage(title, message, urlToImage));

        // assert
        verify(mockEmbedBuilder).appendField(title, message, false);
        verify(mockEmbedBuilder).withImage(urlToImage);

        // verify RequestBuffer.request(RequestBuffer.IVoidRequest) should be call
        PowerMockito.verifyStatic();
        RequestBuffer.request(iVoidRequestCaptor.capture());

        // when iVoidRequest.doRequest() then iChannel.sendMessage(embedObject) should be call
        RequestBuffer.IVoidRequest iVoidRequest = iVoidRequestCaptor.getValue();
        iVoidRequest.doRequest();
        verify(mockIChannel).sendMessage(mockEmbedObject);
    }
}
