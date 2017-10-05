package commands;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchRandom;
import at.mukprojects.giphy4j.exception.GiphyException;
import main.RexCord;
import sx.blah.discord.handle.impl.events
        .guild.channel.message.MessageReceivedEvent;

/**
 * Gif searcher
 */
public class GifCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * What invokes the command
     */
    private static final String COMMAND_NAME =
            "gif";

    /**
     * A brief description of the command's functionality
     */
    private static final String COMMAND_DESCRIPTION =
            "A simple gif browser";

    /**
     * Creates an instance of Gif Command
     * @param rexCord main instace of RexCord
     */
    public GifCommand(RexCord rexCord) {
        this.rexCord = rexCord;
    }

    /**
     * Gets command description
     * @return command description
     */
    @Override
    public final String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * Gets Command Description
     * @return command description
     */
    @Override
    public final String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }

    /**
     * Executes Command
     * @param event passed event
     * @param args passed arguments
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        Giphy giphy = new Giphy(rexCord.getGiphyAPIKey());

        try {
            SearchRandom feed = giphy.searchRandom(args);
            EmbeddedMessage embeddedMessage =
                    new EmbeddedMessage(feed.getData().getImageUrl(),
                    feed.getData().getId(),
                    feed.getData().getImageOriginalUrl());
            rexCord.sendEmbeddedMessage(event.getChannel(),
                    embeddedMessage);

        } catch (GiphyException e) {
            rexCord.sendMessage(event.getChannel(),
                    "An error ocurred while processing that gif.\n"
                            + "Did you properly set your API Key in the "
                            + "configuration file?");
        }
    }
}
