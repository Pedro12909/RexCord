package model;

import java.util.Collection;

/**
 * Created by thales minussi on 28/09/17.
 * https://github.com/tminussi
 * Class responsible for representing the XML configuration file
 */
public final class Configuration {

    /**
     * token
     */
    private String token;

    /**
     * prefix
     */
    private String prefix;

    /**
     * collection of banned commands
     */
    private Collection<String> bannedCommands;

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     * sets the token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix
     * sets the prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @return collection of bannedCommands
     */
    public Collection<String> getBannedCommands() {
        return bannedCommands;
    }

    /**
     * @param bannedCommands
     * sets bannedCommands
     */
    public void setBannedCommands(Collection<String> bannedCommands) {
        this.bannedCommands = bannedCommands;
    }
}
