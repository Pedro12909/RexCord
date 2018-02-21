package utils;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
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
    private static final String CONFIG_NOT_FOUND_ERROR =
            "RexCord: Config file not found. "
            + "Make sure config and permissions files are created "
            + "and located in the correct directory.";

    /**
     * When Configuration file is not properly formatted, this message should be
     * displayed
     */
    private static final String BAD_XML_CONFIG_ERROR =
            "It seems that the configuration file is not properly formatted.\n"
            + "Make sure it follows the example_config.xml formatting.";


    /**  if (configuration.isApiGiphyKeySet()) {
            rexCord.setGiphyAPIKey(configuration.getApiGiphyKey());
        }
     * Creates an instance of ConfigReader
     * @param rexCord main instance of RexCord
     * @throws IOException in case doesnt find config file
     */
    public ConfigReader(RexCord rexCord) throws IOException {
        this.rexCord = rexCord;

        try {
            readFile();
        } catch (UnrecognizedPropertyException e) {
            throw new IOException(BAD_XML_CONFIG_ERROR);
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
        if (configuration.isPrefixSet()) {
            rexCord.setBotPrefix(configuration.getPrefix());
        }
        if (configuration.isListenChannelsSet()) {
            rexCord.setListenChannels(configuration.getListenChannels());
        }
    }

}
