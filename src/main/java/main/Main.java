package main;

import commands.CommandHandler;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;
import utils.BotUtils;
import utils.ConfigReader;
import java.io.FileNotFoundException;

/**
 * Main Class
 */
public class Main {

    /**
     * Private constructor
     */
    private Main() {

    }

    /**
     * Main method
     * @param args passed arguments
     */
    public static void main(String[] args) {

        // Sets start time for uptime command
        BotUtils.setStartTime(System.currentTimeMillis());

        try {
            // Reads config file
            new ConfigReader(BotUtils.DEFAULT_CONFIG_PATH);

            startClient();
        } catch (FileNotFoundException e) { // Config File doesnt exist
            System.out.println(BotUtils.ERROR_MESSAGE);
            System.out.println(BotUtils.CONFIG_NOT_FOUND_ERROR);
            System.out.println(BotUtils.TERMINATING_MESSAGE);

        } catch (ArrayIndexOutOfBoundsException e1) { // Missing Token
            System.out.println(BotUtils.ERROR_MESSAGE);
            System.out.println(e1.getMessage());
            System.out.println(BotUtils.TERMINATING_MESSAGE);

        } catch (DiscordException e2) { // Invalid token
            System.out.println(BotUtils.ERROR_MESSAGE);
            System.out.println("Unexpected error while loading RexCord."
                    + "\nDid you insert a valid token?");
            System.out.println(BotUtils.TERMINATING_MESSAGE);
        }

    }

    /**
     * Starts Bot Client
     */
    private static void startClient() {
        // Creates a new Client
        IDiscordClient client = BotUtils.createDiscordClient();

        // Registers a new listener
        client.getDispatcher().registerListener(new CommandHandler());

        // Logs in
        client.login();
    }
}
