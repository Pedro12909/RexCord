package commands;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchRandom;
import at.mukprojects.giphy4j.exception.GiphyException;
import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Gif searcher
 */
public class GifCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * A brief description of the command's functionality
     */
    private static final String COMMAND_DESCRIPTION =
            "A simple gif browser";

    /**
     * Displayed message when there's an error
     * processing a gif query
     */
    private static final String ERROR_MSG =
            "An error ocurred while processing that gif.\n"
            + "Did you properly set your API Key in the configuration file?";

    /**
     * Creates an instance of Gif Command
     * @param rexCord main instace of RexCord
     */
    public GifCommand(RexCord rexCord) {
        this.rexCord = rexCord;
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
            rexCord.sendMessage(event.getChannel(), ERROR_MSG);
        }
    }
}
