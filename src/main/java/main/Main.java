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
     * Config missing Error Message
     */
    private static final String CONFIG_NOT_FOUND_ERROR
            = "RexCord: Config file not found. "
            + "Make sure config and permissions files are created "
            + "and located in the correct directory.";

    /**
     * Default Error Message
     */
    private static final String ERROR_MESSAGE
            = "RexCord: Unable to start RexCord!";

    /**
     * RexCord terminating message
     */
    private static final String TERMINATING_MESSAGE
            = "RexCord: Terminating RexCord...";

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
        } catch (IOException e) { // Config File doesnt exist
            System.out.println(RexCord.ERROR_MESSAGE);
            System.out.println(RexCord.CONFIG_NOT_FOUND_ERROR);
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
