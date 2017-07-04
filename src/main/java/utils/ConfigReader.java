package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads config file
 */
public class ConfigReader {

    /**
     * Print this message when a field definition is empty
     */
    private static final String MISSING_DEFINITION =
            "Error while loading config.cfg\n[Line %d] Missing %s definition";

    /**
     * Creates an instance of ConfigReader
     * @param configPath The default config file path
     * @throws FileNotFoundException in case doesnt find config file
     */
    public ConfigReader(String configPath) throws FileNotFoundException {
        readFile(configPath);
    }

    /**
     * Reads config file and assigns each parameter value
     * @param configPath Configuration file's path
     * @throws FileNotFoundException if config file is not found
     */
    private void readFile(String configPath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(configPath));

        int lineNumber = 0;

        while (sc.hasNext()) {
            String currentLine = sc.nextLine();
            lineNumber++;

            if (!currentLine.trim().equals("")
                    && !currentLine.startsWith(BotUtils.CONFIG_COMMENT)) {

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
                        BotUtils.setBotToken(option);
                        break;
                    case "prefix":
                        BotUtils.setBotPrefix(option);
                        break;
                    default:
                        break;
                }
            }
        }

        sc.close();
    }
}
