package utils;

import main.RexCord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
     * @throws FileNotFoundException in case doesnt find config file
     */
    public ConfigReader(RexCord rexCord) throws FileNotFoundException {
        this.rexCord = rexCord;
        readFile();
    }

    /**
     * Reads config file and assigns each parameter value
     *
     * @throws FileNotFoundException if config file is not found
     */
    private void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(RexCord.DEFAULT_CONFIG_PATH));

        int lineNumber = 0;

        while (sc.hasNext()) {
            String currentLine = sc.nextLine();
            lineNumber++;

            if (!currentLine.trim().equals("")
                    && !currentLine.startsWith(RexCord.CONFIG_COMMENT)) {

                String[] lineSplitted = currentLine.split("=");
                String parameter = lineSplitted[0].trim();
                String option = null;
                try {
                    option = lineSplitted[1].trim();
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException(
                            String.format(MISSING_DEFINITION,
                                    lineNumber, parameter));
                }

                switch (parameter) { //config parameters should be handled here
                    case "token":
                        rexCord.setBotToken(option);
                        break;
                    case "banned_commands":
                        rexCord.setBotBannedCommands(option);
                    case "prefix":
                        rexCord.setBotPrefix(option);
                        break;
                    default:
                        break;
                }
            }
        }

        sc.close();
    }
}
