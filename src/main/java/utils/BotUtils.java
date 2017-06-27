package utils;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;

public class BotUtils {

    /**
     * Bot's user token
     */
    static final String BOT_TOKEN = "MzIwNzAwMjgwNzcxNzA2ODgx.DDMkDA.Hxy_aoLbMoE5vdhNKjMfprVSgvk";

    /**
     * Only messages starting with this prefix will be tracked
     */
    public static final String BOT_PREFIX = "//";

    /**
     * Handles the creation of a Bot Client
     * @return A new Bot Client
     */
    public static IDiscordClient createDiscordClient() {
        return new ClientBuilder()
                .withToken(BOT_TOKEN)
                .build();
    }

    public static void sendMessage(IChannel channel, String message) {
        try {
            channel.sendMessage(message);
        } catch (DiscordException de) {
            System.out.println("Error sending message. Got error:");
            de.printStackTrace();
        }
    }

}
