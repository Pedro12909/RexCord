package model.Permissions;

import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel.message.
        MessageReceivedEvent;
import sx.blah.discord.handle.obj.IRole;
import utils.XmlParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains configuration data of permissions.xml
 */
public class PermissionConfiguration {

    /**
     * Config missing Error Message
     */
    private static final String PERMISSION_FILE_NOT_FOUND_ERROR
            = "RexCord: Permissions file not found. "
            + "Make sure config and permissions files are created "
            + "and located in the correct directory.";

    /**
     * List of roles on server
     */
    private List<Role> roles;

    /**
     * Get list of roles on server
     * @return list of roles on server
     */
    public final List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets list of roles on server
     * @param roles list of roles on server
     */
    public final void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Checks whether command sent is permissible
     * @param event message event triggered when command is invoke
     * @param command command the user is trying to invoke
     * @return whether user is allowed to invoke command
     */
    public final boolean isAllowed(MessageReceivedEvent event, String command) {
        long userId = event.getAuthor().getLongID();
        ArrayList<String> userRoles = new ArrayList<>();

        for (IRole userRole
                : event.getAuthor().getRolesForGuild(event.getGuild())) {
            userRoles.add(userRole.getName());
        }

        for (Role role : roles) {
            //return true if user is eligible to execute command
            if (containsString(userRoles, role.getName())
                    && (role.getBannedUsers() == null
                        || !role.getBannedUsers().contains(userId))
                    && (containsString(role.getCommands(), "*")
                        || containsString(role.getCommands(), command))
                    ) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether string is in list with no regard to case and
     * preceeding/following spaces
     * @param list list to check string in
     * @param target string to check
     * @return whether string is in the list
     */
    public final boolean containsString(List<String> list, String target) {
        for (String item : list) {
            if (item.trim().equalsIgnoreCase(target.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates instance of PermissionConfiguration
     * @param rexCord main instance of RexCord
     * @return instance of PermissionConfiguration
     * @throws IOException thrown if permissions.xml is not found
     */
    public static PermissionConfiguration createInstance(RexCord rexCord)
            throws IOException {

        PermissionConfiguration result = null;
        try {
            result = XmlParser.parseXml(rexCord.DEFAULT_PERMISSIONS_PATH,
                    PermissionConfiguration.class);
        } catch (IOException e) {
            throw new IOException(PERMISSION_FILE_NOT_FOUND_ERROR);
        }

        return result;
    }
}
