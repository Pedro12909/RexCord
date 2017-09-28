/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import main.RexCord;

/**
 * Class to check banned commands
 */
public class BannedCommands {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Creates an instance of Banned Commands
     * @param rexCord main instance of RexCord
     */
    public BannedCommands(RexCord rexCord) {
        this.rexCord = rexCord;
    }

    /**
     * Checks if a certain command is banned
     *
     * @param command String with the command
     * @return true if command is banned, false if not
     */
    public final boolean isCommandBanned(String command) {
        if (rexCord.getBotBannedCommands() != null) {
            String[] bannedCommands = rexCord.getBotBannedCommands()
                    .split(",");
            for (int i = 0; i < bannedCommands.length; i++) {
                if (bannedCommands[i].replaceFirst("\\s+", "").
                        equalsIgnoreCase(command)) {
                    return true;
                }
            }
        }
        return false;
    }
}
