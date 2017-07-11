package utils;

import java.util.List;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.
        MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;

/**
 * Contains important methods and variables for the Bot
 */
public final class BotUtils {

    /**
     * Bot Client
     */
    private static IDiscordClient client;

    /**
     * System's start time
     */
    private static long startTime;

    /**
     * Bot's user token
     */
    private static String botToken;
    /**
     * Bot's banned commands
     */
    private static String botBannedCommands;
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
     * Prevents class from being instantiated
     */
    private BotUtils() {

    }

    /**
     * Handles the creation of a Bot Client
     *
     * @return A new Bot Client
     */
    public static IDiscordClient createDiscordClient() {
        client = new ClientBuilder()
                .withToken(botToken)
                .build();

        return client;
    }

    /**
     * Gets Bot Client
     *
     * @return IDiscordClient
     */
    public static IDiscordClient getClient() {
        return client;
    }

    /**
     * Sets a new Bot Token
     *
     * @param newToken new Bot Token to be set
     */
    public static void setBotToken(String newToken) {
        BotUtils.botToken = newToken;
    }

    /**
     * Sets the bot banned commands
     *
     * @param bannedCommands String with the banned bot commands
     */
    public static void setBotBannedCommands(String bannedCommands) {
        BotUtils.botBannedCommands = bannedCommands;
    }

    /**
     * Gets the bot banned commands
     *
     * @return String with bot banned commands
     */
    public static String getBotBannedCommands() {
        return botBannedCommands;
    }

    /**
     * Gets bot prefix
     *
     * @return Bot prefix
     */
    public static String getBotPrefix() {
        return botPrefix;
    }

    /**
     * Sets bot prefix
     *
     * @param botPrefix new bot prefix
     */
    public static void setBotPrefix(String botPrefix) {
        BotUtils.botPrefix = botPrefix;
    }

    /**
     * Gets system start time, in mm
     *
     * @return system start time, in mm
     */
    public static long getStartTime() {
        return startTime;
    }

    /**
     * S system start time, in mm
     *
     * @param startTime system start time, in mm
     */
    public static void setStartTime(long startTime) {
        BotUtils.startTime = startTime;
    }

    /**
     * Sends a text message
     *
     * @param channel text channel
     * @param message message
     */
    public static void sendMessage(IChannel channel, String message) {
        try {
            channel.sendMessage(message);
        } catch (DiscordException de) {
            System.out.println("RexCord: Error sending message. Got error:");
            de.printStackTrace();
        }
    }

    /**
     * Returns the roles of a certain user that triggered an event
     *
     * @param event received event triggered by an user
     * @return List of Roles the user who triggered an event has
     */
    private static List<IRole> getRolesUserOnEvent(MessageReceivedEvent event) {
        return event.getGuild().getRolesForUser(event.getAuthor());
    }

    /**
     * Checks if a user has acess to a certain role
     *
     * @param event received event triggered by an user
     * @param role String with the desired role wanted to be compared
     * @return a boolean true if has acess, false if not
     */
    public static boolean hasUserAcessToRoleOnEvent(MessageReceivedEvent event,
            String role) {
        List<IRole> userRoles = getRolesUserOnEvent(event);
        for (int i = 0; i < userRoles.size(); i++) {
            if (userRoles.get(i).getName().replaceFirst("@", "")
                    .equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sends a message to a certain user
     *
     * @param user user to send the message
     * @param message message being sent
     */
    public static void sendMessageToUser(IUser user, String message) {
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
