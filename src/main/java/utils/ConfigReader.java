package utils;

import main.RexCord;
import model.Configuration;

import java.io.IOException;

/**
 * Reads config file
 */
public class ConfigReader {

    /**
     * Instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Config missing Error Message
     */
    private static final String CONFIG_NOT_FOUND_ERROR
            = "RexCord: Config file not found. "
            + "Make sure config and permissions files are created "
            + "and located in the correct directory.";

    /**
     * Creates an instance of ConfigReader
     * @param rexCord main instance of RexCord
     * @throws IOException in case doesnt find config file
     */
    public ConfigReader(RexCord rexCord) throws IOException {
        this.rexCord = rexCord;

        try {
            readFile();
        } catch (IOException e) {
            throw new IOException(CONFIG_NOT_FOUND_ERROR);
        }
    }

    /**
     * Reads config file and assigns each parameter value
     *
     * @throws IOException if config file is not found
     */
    private void readFile() throws IOException {
        Configuration configuration = XmlParser
                .parseXml(RexCord.DEFAULT_CONFIG_PATH, Configuration.class);
        if (configuration.isTokenSet()) {
            rexCord.setBotToken(configuration.getToken());
        }
        if (configuration.isBannedCommandsSet()) {
            rexCord.setBotBannedCommands(configuration.getBannedCommands());
        }
        if (configuration.isPrefixSet()) {
            rexCord.setBotPrefix(configuration.getPrefix());
        }
        if (configuration.isListenChannelsSet()) {
            rexCord.setListenChannels(configuration.getListenChannels());
        }
        if (configuration.isApiGiphyKeySet()) {
            rexCord.setGiphyAPIKey(configuration.getApiGiphyKey());
        }
    }

}
