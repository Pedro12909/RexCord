package commands;

import sx.blah.discord.handle.impl.events.guild.channel.message
        .MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;
import utils.BotUtils;
import utils.DiscordMarkdown;

/**
 * Requests something to admin
 */
public class RequestCommand implements BotCommand {

    /**
     * Represents the command names
     */
    private static final String COMMAND_NAME = "request";
    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION =
            "Requests a certain command"
            + "to Admin";

    /**
     * Gets command name
     *
     * @return command name
     */
    @Override
    public final String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * Gets command description
     *
     * @return command description
     */
    @Override
    public final String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }

    /**
     * Runs the command triggered by the user
     *
     * @param event the event triggered by the user
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        BotUtils.sendMessageToUser(BotUtils.getClient().getApplicationOwner(),
                buildMessage(event.getAuthor(), event.getMessage().getContent()
                        .replaceAll(BotUtils.getBotPrefix(), "")
                        .replaceAll(COMMAND_NAME, "")));
    }

    /**
     * Builds the message being sent
     *
     * @param user user that requested something
     * @param message user request
     * @return String with a proper request message
     */
    private String buildMessage(IUser user, String message) {
        return user.mention() + " just requested you something!\n"
                + DiscordMarkdown
                        .boldItalic(message
                                .replaceAll(BotUtils.getBotPrefix(), "")
                                .replaceAll(COMMAND_NAME, ""));
    }
}
