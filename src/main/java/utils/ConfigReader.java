package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads config file
 */
public class ConfigReader {

    /**
     * Creates an instance of ConfigReader
     *
     * @param configPath The default config file path
     * @throws FileNotFoundException in case doesnt find config file
     */
    public ConfigReader(String configPath) throws FileNotFoundException {
        readFile(configPath);
    }

    /**
     * Reads config file and assigns each parameter value
     *
     * @param configPath Configuration file's path
     * @throws FileNotFoundException if config file is not found
     */
    private void readFile(String configPath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(configPath));

        while (sc.hasNext()) {
            String currentLine = sc.nextLine();

            if (!currentLine.trim().equals("")
                    && !currentLine.startsWith(BotUtils.CONFIG_COMMENT)) {

                String[] lineSplitted = currentLine.split("=");
                String parameter = lineSplitted[0].trim();
                String option = null;
                try {
                    option = lineSplitted[1].trim();
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException(
                            BotUtils.MISSING_TOKEN);
                }

                switch (parameter) { //config parameters should be handled here
                    case "token":
                        BotUtils.setBotToken(option);
                        break;
                    case "banned_commands":
                        BotUtils.setBotBannedCommands(option);
                        break;
                    default:
                        break;
                }
            }
        }

        sc.close();
    }
}
