/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import utils.BotUtils;

/**
 * Class to check banned commands
 */
public class BannedCommands {

    /**
     * Checks if a certain command is banned
     *
     * @param command String with the command
     * @return true if command is banned, false if not
     */
    public static boolean isCommandBanned(String command) {
        if (BotUtils.getBotBannedCommands() != null) {
            String[] bannedCommands = BotUtils.getBotBannedCommands()
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
    /**
     * Hides default constructor
     */
    private BannedCommands() { };
}
