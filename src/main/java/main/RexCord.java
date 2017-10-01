package main;

import commands.BannedCommands;
import commands.CommandHandler;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.
        MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import utils.RemindHandler;
import utils.ReminderDispatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains important methods and variables for the Bot
 */
public final class RexCord {

    /**
     * Bot Client
     */
    private IDiscordClient client;

    /**
     * RexCord's Banned Commands
     */
    private BannedCommands bannedCommands;

    /**
     * Instance of Command Handler
     */
    private CommandHandler commandHandler;

    /**
     * List of Text Channels that RexCord listens to
     */
    private List<Long> listenChannels;

    /**
     * Instance of Remind Handler
     */
    private RemindHandler remindHandler;

    /**
     * System's start time
     */
    private long startTime;

    /**
     * Bot's user token
     */
    private String botToken;

    /**
     * Bot's banned commands
     */
    private String botBannedCommands;

    /**
     * Public API Key from Giphy
     */
    private String giphyAPIKey;

    /**
     * Only messages starting with this prefix will be handled
     */
    private static String botPrefix = "//";

    /**
     * Line comment in config file
     */
    public static final String CONFIG_COMMENT = "#";

    /**
     * Configuration's File Path
     */
    public static final String DEFAULT_CONFIG_PATH
            = System.getProperty("user.dir") + "/config/config.cfg";

    /**
     * Config missing Error Message
     */
    public static final String CONFIG_NOT_FOUND_ERROR
            = "RexCord: Config file not found. "
            + "Make sure it is created and located in "
            + "the correct directory.";

    /**
     * Unexpected error message
     */
    public static final String UNEXPECTED_ERROR_MESSAGE =
            "Unexpected error while loading RexCord."
            + "\nDid you insert a valid token?";

    /**
     * Default Error Message
     */
    public static final String ERROR_MESSAGE
            = "RexCord: Unable to start RexCord!";

    /**
     * RexCord terminating message
     */
    public static final String TERMINATING_MESSAGE
            = "RexCord: Terminating RexCord...";

    /**
     * Prevents class from being instantiated
     */
    public RexCord() {
        bannedCommands = new BannedCommands(this);
        commandHandler = new CommandHandler(this);
        listenChannels = new ArrayList<>();
        remindHandler = new RemindHandler(this);

        Thread dispatcher = new Thread(new ReminderDispatcher(remindHandler));
        dispatcher.start();
    }

    /**
     * Handles the creation of a Bot Client
     *
     * @return A new Bot Client
     */
    public IDiscordClient createDiscordClient() {
         client = new ClientBuilder()
                .withToken(botToken)
                .build();

         return client;
    }

    /**
     * Gets Bot Client
     * @return IDiscordClient
     */
    public IDiscordClient getClient() {
        return client;
    }

    /**
     * Gets Banned Commands
     * @return Banned Commands Class
     */
    public BannedCommands getBannedCommands() {
        return bannedCommands;
    }

    /**
     * Sets a new Bot Token
     *
     * @param newToken new Bot Token to be set
     */
    public void setBotToken(String newToken) {
        this.botToken = newToken;
    }

    /**
     * Sets the bot banned commands
     *
     * @param bannedCommands String with the banned bot commands
     */
    public void setBotBannedCommands(String bannedCommands) {
        this.botBannedCommands = bannedCommands;
    }

    /**
     * Gets the bot banned commands
     *
     * @return String with bot banned commands
     */
    public String getBotBannedCommands() {
        return botBannedCommands;
    }

    /**
     * Gets current Giphy API Key
     * @return giphy api key
     */
    public String getGiphyAPIKey() {
        return giphyAPIKey;
    }

    /**
     * Sets giphy API key
     * @param giphyAPIKey giphy API key
     */
    public void setGiphyAPIKey(String giphyAPIKey) {
        this.giphyAPIKey = giphyAPIKey;
    }

    /**
     * Gets CommandHandler
     * @return CommandHandler
     */
    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    /**
     * Gets bot prefix
     * @return Bot prefix
     */
    public String getBotPrefix() {
        return botPrefix;
    }

    /**
     * Gets List of ListenChannels
     * @return list
     */
    public List<Long> getListenChannels() {
        return listenChannels;
    }

    /**
     * Gets main ReminderHandler instance
     * @return main ReminderHandler instance
     */
    public RemindHandler getRemindHandler() {
        return remindHandler;
    }

    /**
     * Sets List of ListenChannels
     * @param listenChannels listenChannels
     */
    public void setListenChannels(List<Long> listenChannels) {
        this.listenChannels = listenChannels;
    }

    /**
     * Sets bot prefix
     * @param botPrefix new bot prefix
     */
    public void setBotPrefix(String botPrefix) {
        this.botPrefix = botPrefix;
    }

    /**
     * Gets system start time, in ms
     *
     * @return system start time, in ms
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Sets system start time, in ms
     *
     * @param startTime system start time, in ms
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Sends a text message
     *
     * @param channel text channel
     * @param message message
     */
    public void sendMessage(IChannel channel, String message) {
        try {
            channel.sendMessage(message);
        } catch (DiscordException de) {
            System.out.println("RexCord: Error sending message. Got error:");
            de.printStackTrace();
        }
    }

    /**
     * Sends a message inside a code block
     * @param channel text channel
     * @param message message
     */
    public void sendMessageWithBlock(IChannel channel, String message) {
        message = "`" + message + "`";

        sendMessage(channel, message);
    }

    /**
     * Gets RexCord's Voice Channel
     * @param event triggered event
     * @return IVoiceChannel
     */
    public IVoiceChannel getBotVoiceChannel(MessageReceivedEvent event) {
        return getClient()
                .getOurUser()
                .getVoiceStateForGuild(event.getGuild())
                .getChannel();
    }

    /**
     * Returns the IVoiceChannel in which the user that
     * triggered the event is in
     * @param event triggered event
     * @return user's IVoiceChannel
     */
    public IVoiceChannel getUserVoiceChannelInEvent(MessageReceivedEvent
                                                            event) {
        return event.getAuthor().getVoiceStateForGuild(event.getGuild())
                .getChannel();

    }

    /**
     * Sends a message to a certain user
     *
     * @param user user to send the message
     * @param message message being sent
     */
    public void sendMessageToUser(IUser user, String message) {
        try {
            //Constructor requires the bot IDiscordClient
            new MessageBuilder(client).withChannel(client
                    .getOrCreatePMChannel(user))
                    .appendContent(message)
                    .send();
        } catch (DiscordException e) {
            //Stills needs to be treated
            //This exception occurs when the content is blank (no content)
        }
    }
}
