package ui;

import commands.CommandHandler;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import utils.BotUtils;

public class Main {

    public static void main(String[] args) {
        // Creates a new Client
        IDiscordClient client = BotUtils.createDiscordClient();

        // Registers a new listener
        client.getDispatcher().registerListener(new CommandHandler());

        // Logs in
        client.login();
    }
}
