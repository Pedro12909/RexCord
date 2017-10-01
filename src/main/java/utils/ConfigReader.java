package utils;

import main.RexCord;
import model.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads config file
 */
public class ConfigReader {

    /**
     * Instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Print this message when a field definition is empty
     */
    private static final String MISSING_DEFINITION =
            "Error while loading config.cfg\n[Line %d] Missing %s definition";

    /**
     * Creates an instance of ConfigReader
     * @param rexCord main instance of RexCord
     * @throws IOException in case doesnt find config file
     */
    public ConfigReader(RexCord rexCord) throws IOException {
        this.rexCord = rexCord;
        readFile();
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
        } else if (configuration.isBannedCommandsSet()) {
            rexCord.setBotBannedCommands(configuration.getBannedCommands());
        } else if (configuration.isPrefixSet()) {
            rexCord.setBotPrefix(configuration.getPrefix());
        } else if (configuration.isListenChannelsSet()) {
            rexCord.setListenChannels(configuration.getListenChannels());
        } else if (configuration.isApiGiphyKeySet()) {
            rexCord.setGiphyAPIKey(configuration.getApiGiphyKey());
        }
    }

    /**
     * Reads list of channels that RexCord should listen from config
     * @param option channels defined in config
     * @return List of channel IDs
     */
    private List<Long> readListenChannels(String option) {
        List<Long> channels = new ArrayList<>();

        String[] channelIDs = option.split(",");

        for (String id : channelIDs) {
            long longID = Long.parseLong(id.trim());

            try {
                channels.add(longID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return channels;
    }
}
