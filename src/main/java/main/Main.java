package main;

import commands.CommandHandler;
import model.Permissions.PermissionConfiguration;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;
import utils.ConfigReader;
import java.io.IOException;

/**
 * Main Class
 */
public class Main {

    /**
     * Indicates that this class should not be instantiated
     */
    private Main() {

    }

    /**
     * Main method
     * @param args passed arguments
     */
    public static void main(String[] args) {
        RexCord rexCord = new RexCord();

        // Sets start time for uptime command
        rexCord.setStartTime(System.currentTimeMillis());

        try {
            // Reads config file
            new ConfigReader(rexCord);
            rexCord.setPermissions(PermissionConfiguration.
                                            createInstance(rexCord));

            startClient(rexCord);
        } catch (IOException e) { // Config/Permissions file could not be read
            System.out.println(RexCord.ERROR_MESSAGE);
            System.out.println(e.getMessage());
            System.out.println(RexCord.TERMINATING_MESSAGE);

        } catch (ArrayIndexOutOfBoundsException e1) { // Bad config
            System.out.println(RexCord.ERROR_MESSAGE);
            System.out.println(e1.getMessage());
            System.out.println(RexCord.TERMINATING_MESSAGE);

        } catch (DiscordException e2) { // Invalid token
            System.out.println(RexCord.ERROR_MESSAGE);
            System.out.println(RexCord.UNEXPECTED_ERROR_MESSAGE);
            System.out.println(RexCord.TERMINATING_MESSAGE);
        }
    }

    /**
     * Starts Bot Client
     * @param rexCord main instance of RexCord
     */
    private static void startClient(RexCord rexCord) {
        // Creates a new Client
        IDiscordClient client = rexCord.createDiscordClient();

        // Registers a new listener
        client.getDispatcher().registerListener(new CommandHandler(rexCord));

        // Logs in
        client.login();
    }
}
