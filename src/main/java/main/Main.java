package main;

import commands.CommandHandler;
import sx.blah.discord.api.IDiscordClient;
import utils.BotUtils;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        // Check for needed files (config)
        if (!findConfigFile()) {

        }

        // Generate those files if they do not exist

        // Read files

        startClient();
    }

    private static boolean findConfigFile() {
        File configFile = new File(System.getProperty("user.dir"));
        return configFile.exists();
    }

    private static void createNewConfig() {
        final String currentDir = System.getProperty("user.dir");

        
    }

    private static void startClient() {
        // Creates a new Client
        IDiscordClient client = BotUtils.createDiscordClient();

        // Registers a new listener
        client.getDispatcher().registerListener(new CommandHandler());

        // Logs in
        client.login();
    }
}
