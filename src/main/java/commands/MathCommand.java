package commands;

import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel
        .message.MessageReceivedEvent;
import utils.MathHandler;

/**
 * Calculates a certain expression that the user typed
 */
public class MathCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION
            = "Calculates a given mathematical operation";

    /**
     * Creates an instance of Math Command class
     * @param rexCord main instance of rexCord
     */
    public MathCommand(RexCord rexCord) {
        this.rexCord = rexCord;
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
     * Runs the command triggered by the user
     *
     * @param event the event triggered by the user
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        rexCord.sendMessage(event.getChannel(),
                MathHandler.handleOperation(args));
    }

}
