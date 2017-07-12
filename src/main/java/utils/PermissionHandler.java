/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import sx.blah.discord.handle.impl.events.guild.channel.message
        .MessageReceivedEvent;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.MissingPermissionsException;

/**
 * Handles missing permissions by telling the user who invoked it that the bot
 * doesn't have acess to perform a certain event due to a certain missing
 * permission
 */
public class PermissionHandler {
    /**
     * Default message
     */
    private static final String BOT_NO_ACESS = "Bot doesnt have acess to ";
    /**
     * Handles missing permissions
     * @param e MissingPermissionsException catched
     * @param event event triggered by the user
     */
    public static void handle(MissingPermissionsException e,
            MessageReceivedEvent event) {
        Object[] missingPermissions = e.getMissingPermissions().toArray();
        for (int i = 0; i < missingPermissions.length; i++) {
            switch ((Permissions) missingPermissions[i]) {
                case ADD_REACTIONS:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "add reactions!");
                    break;
                case ADMINISTRATOR:
                    //Still needs management
                    break;
                case ATTACH_FILES:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "attach files");
                    break;
                case BAN:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "ban users!");
                    break;
                case CHANGE_NICKNAME:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "change nicknames!");
                    break;
                case CREATE_INVITE:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "create invitations!");
                    break;
                case EMBED_LINKS:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "create embed links!");
                    break;
                case KICK:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "kick users!");
                    break;
                case MANAGE_CHANNEL:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "manage current channel!");
                    break;
                case MANAGE_CHANNELS:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "manage channels!");
                    break;
                case MANAGE_EMOJIS:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "manage emojis!");
                    break;
                case MANAGE_MESSAGES:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "manage messages!");
                    break;
                default:
                    break;
            }
            return;
        }
    }

    /**
     * Hides default constructor
     */
    private PermissionHandler() {
    }
}
