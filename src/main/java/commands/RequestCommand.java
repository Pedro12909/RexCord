package commands;

import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel.message
        .MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;
import utils.DiscordMarkdown;

/**
 * Requests something to admin
 */
public class RequestCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

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
     * Creates an instance of Request Command class
     * @param rexCord main instance of RexCord
     */
    public RequestCommand(RexCord rexCord) {
        this.rexCord = rexCord;
    }

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
     * Runs the command triggered by the user
     *
     * @param event the event triggered by the user
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        rexCord.sendMessageToUser(rexCord.getClient().getApplicationOwner(),
                buildMessage(event.getAuthor(), event.getMessage().getContent()
                        .replaceAll(rexCord.getBotPrefix(), "")
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
                                .replaceAll(rexCord.getBotPrefix(), "")
                                .replaceAll(COMMAND_NAME, ""));
    }
}
