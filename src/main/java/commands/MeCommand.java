package commands;

import main.RexCord;
import sx.blah.discord.handle.impl.events
        .guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Displays user information
 */
public class MeCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Command Description
     */
    private static final String COMMAND_DESCRIPTION =
            "Displays user information in an Embedded Message";

    /**
     * Message Color
     */
    private static final Color MSG_COLOR =
            new Color(0, 204, 204);

    /**
     * Creates an instance of the MeCommand class
     * @param rexCord main instance of rexcord
     */
    public MeCommand(RexCord rexCord) {
        this.rexCord = rexCord;
    }

    /**
     * Gets command description
     * @return
     */
    @Override
    public final String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }

    /**
     * Called when command is invoked
     * @param event passed event
     * @param args passed arguments
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        IUser user = event.getAuthor();

        final String displayName = user.getDisplayName(event.getGuild());
        final String discriminator = user.getDiscriminator();
        final String userID = String.valueOf(user.getLongID());
        final String joinDate = user.getCreationDate()
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        final String avatarURL = user.getAvatarURL();
        final String isBot = String.valueOf(user.isBot());
        final List<IRole> userRoles = user.getRolesForGuild(event.getGuild());

        EmbedBuilder builder = new EmbedBuilder();

        builder.withAuthorIcon(avatarURL);
        builder.withAuthorName(displayName + " User Information");
        builder.withThumbnail(avatarURL);

        builder.withColor(MSG_COLOR);

        builder.appendField("\n:bust_in_silhouette: "
                + "Display Name :bust_in_silhouette: ", displayName, true);
        builder.appendField(":spy: Discriminator :spy:\n", discriminator,
                true);

        builder.appendField("\n:label: User ID :label:", userID, true);
        builder.appendField(":clock1: Joined this guild on :clock1:", joinDate,
                true);

        builder.appendField("\n:robot: Is a Bot? :robot:", isBot, true);
        builder.appendField(":performing_arts: User Roles :performing_arts:",
                rolesListToString(userRoles), true);

        builder.withFooterIcon(rexCord.getClient().getOurUser().getAvatarURL());
        builder.withFooterText("RexCord's Me Command");

        RequestBuffer.request(() ->
                event.getChannel().sendMessage(builder.build()));
    }

    /**
     * Given a list of IRole, adds each IRole's name to a list
     * of Strings and then returns it
     * @param roles list of IRoles
     * @return List of strings</String>
     */
    private String rolesListToString(List<IRole> roles) {
        if (roles.isEmpty()) {
            return "No Roles";
        }

        StringBuilder builder = new StringBuilder();

        for (IRole role : roles) {
            String roleName = role.getName();
            // @everyone should not be added, to prevent pinging everyone
            if (!roleName.equals("@everyone")) {
                builder.append(roleName);
                builder.append(" ");
            }
        }

        return builder.toString();
    }
}
