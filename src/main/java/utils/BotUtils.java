import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class BotUtils {

    /**
     * Bot's user token
     */
    static final String BOT_TOKEN = "MzIwNzAwMjgwNzcxNzA2ODgx.DDLkTg.hStNQMS0K70Qfz5GxOWZVKiX-yM";

    /**
     * Only messages starting with this prefix will be tracked
     */
    static final String BOT_PREFIX = "//";

    /**
     * Handles the creation of a Bot Client
     * @return A new Bot Client
     */
    static IDiscordClient createDiscordClient() {
        return new ClientBuilder()
                .withToken(BOT_TOKEN)
                .build();
    }
}
