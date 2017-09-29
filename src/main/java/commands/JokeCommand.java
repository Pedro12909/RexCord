package commands;

import main.RexCord;
import org.json.JSONArray;
import org.json.JSONObject;
import sx.blah.discord.handle.impl.events
        .guild.channel.message.MessageReceivedEvent;
import utils.JsonReader;

import java.io.IOException;
import java.util.Random;

/**
 * Returns a joke from a given sub Reddit
 */
public class JokeCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Represents the command name
     */
    private static final String COMMAND_NAME = "joke";

    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION =
            "Posts a random joke from a subreddit";

    /**
     * Creates an instance of Joke Command class
     * @param rexCord main instance of RexCord
     */
    public JokeCommand(RexCord rexCord) {
        this.rexCord = rexCord;
    }

    /**
     * Gets Command Name
     * @return command name
     */
    @Override
    public final String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * Gets Command description
     * @return command description
     */
    @Override
    public final String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }

    /**
     * Executes command
     * @param event passed event
     * @param args passed arguments
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        String sub;
        String name = args;

        if (name.equalsIgnoreCase("dad")) {
            sub = "dadjokes";
        } else if (name.equalsIgnoreCase("clean")) {
            sub = "cleanjokes";
        } else if (name.equalsIgnoreCase("mom")) {
            sub = "mommajokes";
        } else if (name.equalsIgnoreCase("classy")) {
            sub = "classyjokes";
        } else {
            sub = "jokes";
        }

        try {
            JSONObject json = JsonReader
                    .readJsonFromUrl("https://reddit.com/r/" + sub + ".json");
            JSONArray children = json.getJSONObject("data")
                    .getJSONArray("children");

            Random r = new Random();
            int random = r.nextInt(children.length());

            JSONObject childData = children.getJSONObject(random)
                    .getJSONObject("data");
            String title = (String) childData.get("title");
            String selftext = (String) childData.get("selftext");
            rexCord.sendMessage(event.getChannel(),
                    title + "\n" + selftext);

        } catch (IOException e) {
            rexCord.sendMessage(event.getChannel(),
                    "Could not find the sub");
        }
    }

}
