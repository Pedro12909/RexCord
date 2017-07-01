package commands;

import sx.blah.discord.handle.impl.events.guild.channel.message.
        MessageReceivedEvent;
import utils.BotUtils;
import utils.DiscordMarkdown;

/**
 * Informs user about the RexCord information and documentation
 */
public class AboutCommand implements BotCommand {

    /**
     * Used to call this command via a message
     */
    private static final String COMMAND_NAME = "about";

    /**
     * String with Discord4J GitHub Repository link
     */
    private static final String DISCORD4J_GITHUB_REPOSITORY =
            "https://github.com/austinv11/Discord4J";
    /**
     * String with austinv11 GitHub Repository link
     */
    private static final String AUSTINV11_GITHUB_REPOSITORY =
            "https://github.com/austinv11";
    /**
     * String with Just Some Music Bot GitHub Repository
     */
    private static final String JUST_SOME_MUSIC_BOT_REPOSITORY =
            "https://github.com/Just-Some-Bots/MusicBot";

    /**
     * Gets command name
     * @return command name
     */
    @Override
    public final String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * Informs the user who triggered this event with
     * about the RexCord information and documentation
     * @param event received event
     */
    @Override
    public final void runCommand(MessageReceivedEvent event) {
        BotUtils.sendMessage(event.getChannel(), "RexCord is a Discord Bot "
                + "written in java, that uses the popular interface "
                + DiscordMarkdown.boldItalic("Discord4J") + " developed by "
                + DiscordMarkdown.bold("austinv11") + "."
                + "\nIt is inspired by the well known music bot, "
                + DiscordMarkdown.boldItalic("Just-Some-Bots Music Bot")
                + " hence its name, RexCord"
                + "\nDiscord4J on GitHub: " + DISCORD4J_GITHUB_REPOSITORY
                + "\nAustinv11 on GitHub: " + AUSTINV11_GITHUB_REPOSITORY
                + "\nJust Some Bots Music Bot on GitHub: "
                + JUST_SOME_MUSIC_BOT_REPOSITORY);
    }
}
