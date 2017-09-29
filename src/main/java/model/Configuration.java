package model;

import java.util.Collection;

/**
 * Created by thales minussi on 28/09/17.
 * https://github.com/tminussi
 */
public class Configuration {

    private String token;

    private String prefix;

    private Collection<String> bannedCommands;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Collection<String> getBannedCommands() {
        return bannedCommands;
    }

    public void setBannedCommands(Collection<String> bannedCommands) {
        this.bannedCommands = bannedCommands;
    }
}
