package events;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import utils.BotUtils;

public class CommandHandler {

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {

        
        BotUtils.sendMessage(event.getChannel(), "Hello!");
    }
}
