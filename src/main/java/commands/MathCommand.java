/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import sx.blah.discord.handle.impl.events.guild.channel.message
        .MessageReceivedEvent;
import utils.BotUtils;
import utils.MathHandler;

/**
 * Calculates a certain expression that the user typed
 */
public class MathCommand implements BotCommand {

    /**
     * Represents the command name
     */
    private static final String COMMAND_NAME = "math";
    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION
            = "Calculates a certain mathematical operation";

    /**
     * Gets command name
     *
     * @return A String with the Command name
     */
    @Override
    public final String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * Gets command name
     *
     * @return A String with the Command description
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
        BotUtils.sendMessage(event.getChannel(),
                MathHandler.handleOperation(args));
    }

}
