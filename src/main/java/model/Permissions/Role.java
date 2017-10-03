package model.Permissions;

import java.util.List;

/**
 * Encapsulates all fields for a role in permissions.xml.
 */
public class Role {

    /**
     * Name of the role
     */
    private String name;

    /**
     * List of banned user ids belonging to role
     */
    private List<Long> bannedUsers;

    /**
     * List of commands avaliable to the role
     */
    private List<String> commands;

    /**
     * Get name of role
     * @return name name of the role
     */
    public final String getName() {
        return name;
    }

    /**
     * Set name of role
     * @param name name of the role
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * Get list of banned user ids associated with the role
     * @return bannedUsers list of users banned from executing commands in role
     */
    public final List<Long> getBannedUsers() {
        return bannedUsers;
    }

    /**
     * Set the list of banned user ids associated with the role
     * @param bannedUsers list of banned user ids
     */
    public final void setBannedUsers(List<Long> bannedUsers) {
        this.bannedUsers = bannedUsers;
    }

    /**
     * Get the list of commands avaliable to the role
     * @return list of avaliable commands
     */
    public final List<String> getCommands() {
        return commands;
    }

    /**
     * Set the lists of permissiable commands for the role
     * @param commands list of commands
     */
    public final void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
