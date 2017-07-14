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
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "administrator commands");
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
                case MANAGE_NICKNAMES:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "manage nicknames!");
                    break;
                case MANAGE_PERMISSIONS:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "manage permissions!");
                    break;
                case MANAGE_ROLES:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "manage roles!");
                    break;
                case MANAGE_SERVER:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "manage server!");
                    break;
                case MANAGE_WEBHOOKS:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "manage webhooks!");
                    break;
                case MENTION_EVERYONE:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "mention everyone!");
                    break;
                case READ_MESSAGES:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "read messages!");
                    break;
                case READ_MESSAGE_HISTORY:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "read message history!");
                    break;
                case SEND_MESSAGES:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "send messages!");
                    break;
                case SEND_TTS_MESSAGES:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "send TTS messages!");
                    break;
                case USE_EXTERNAL_EMOJIS:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "use external emojis!");
                    break;
                case VIEW_AUDIT_LOG:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "view audit log!");
                    break;
                case VOICE_CONNECT:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "voice connect!");
                    break;
                case VOICE_DEAFEN_MEMBERS:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "voice defean members!");
                    break;
                case VOICE_MOVE_MEMBERS:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "voice move members!");
                    break;
                case VOICE_MUTE_MEMBERS:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "mute members!");
                    break;
                case VOICE_SPEAK:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "voice speak!");
                    break;
                case VOICE_USE_VAD:
                    BotUtils.sendMessage(event.getChannel(), BOT_NO_ACESS
                            + "voice use vad!");
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
