package commands;

import main.RexCord;
import sx.blah.discord.handle.impl.events.guild
        .channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

import java.awt.Color;
import java.time.format.DateTimeFormatter;

/**
 * Shows information about the current guild
 */
public class GuildCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Message Color
     */
    private static final Color MSG_COLOR =
            new Color(0, 153, 0);

    /**
     * Creates an instance of the GuildCommand
     * @param rexCord instance of RexCord
     */
    public GuildCommand(RexCord rexCord) {
        this.rexCord = rexCord;
    }

    /**
     * Returns the Command Description
     * @return command description
     */
    @Override
    public final String getCommandDescription() {
        return null;
    }

    /**
     * Execution of the command
     * @param event passed event
     * @param args passed arguments
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        final IGuild guild = event.getGuild();

        final String name = guild.getName();
        final String id = String.valueOf(guild.getLongID());
        final String icon = guild.getIcon();
        final String region = guild.getRegion().getName();
        final String voiceChannels =
                String.valueOf(guild.getVoiceChannels().size());
        final String textChannels =
                String.valueOf(guild.getChannels().size());
        final String roles =
                RexCord.rolesListToString(guild.getRoles());
        final String owner = guild.getOwner().getDisplayName(guild);
        final String users = String.valueOf(guild.getUsers().size());
        final String creationDate = guild.getCreationDate()
                        .format(DateTimeFormatter.ISO_LOCAL_DATE);
        String afkChannelName = "Null";

        // If guild has no afk channel set, getAFKChannel() returns null
        IChannel afkChannel = guild.getAFKChannel();
        if (afkChannel != null) {
            afkChannelName = afkChannel.getName();
        }


        EmbedBuilder builder = new EmbedBuilder();

        builder.withAuthorIcon(icon);
        builder.withThumbnail(icon);
        builder.withAuthorName(name + "Guild Information");
        builder.withColor(MSG_COLOR);

        builder.appendField("Guild Name", name, true);
        builder.appendField("Guild ID", id, true);

        builder.appendField("Owner", owner, true);
        builder.appendField("Region", region, true);

        //builder.appendField("Roles", roles, false);
        builder.appendField("AFK Channel", afkChannelName, true);

        builder.appendField("# Text Channels", textChannels, false);
        builder.appendField("# Voice Channels", voiceChannels, true);

        builder.appendField("# Users", users, false);
        builder.appendField("Creation Date", creationDate, true);

        RequestBuffer.request(() ->
                event.getChannel().sendMessage(builder.build()));
    }
}
