package commands;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import main.RexCord;
import sx.blah.discord.handle.impl.events.guild.channel
        .message.MessageReceivedEvent;
import utils.RemindHandler;
import utils.Reminder;

import java.util.Date;
import java.util.List;

/**
 * Reminder Command
 */
public class RemindCommand implements BotCommand {

    /**
     * Main instance of RexCord
     */
    private RexCord rexCord;

    /**
     * Represents the command description
     */
    private static final String COMMAND_DESCRIPTION =
            "Gives you a reminder at specified date(UTC timezone)";

    /**
     * Creates an instance of Joke Command class
     * @param rexCord main instance of RexCord
     */
    public RemindCommand(RexCord rexCord) {
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
     * Executes command
     * @param event passed event
     * @param args passed arguments
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        RemindHandler remindHandler = rexCord.getRemindHandler();

        //Target message inside quotation marks
        int messageStart = args.indexOf("\"");
        int messageEnd = args.lastIndexOf("\"");

        if (messageStart == messageEnd
                || messageStart == -1
                || messageEnd == -1) {
            rexCord.sendMessage(event.getChannel(),
                    "Message not detected");
            return;
        }

        try {
            //Do +1 as substring is on [start, end-1] interval
            String message = args.substring(messageStart + 1, messageEnd);
            String dateQuery = args.substring(0, messageEnd);

            //Use Natty Date Parser
            Parser parser = new Parser();
            List<DateGroup> groups = parser.parse(dateQuery);

            //Get Natty's interpreted Date
            Date targetDate = groups.get(0).getDates().get(0);

            String confirm = String.format("Ok, I'll remind you on %s: \"%s\"",
                                            targetDate.toString(), message);
            rexCord.sendMessage(event.getChannel(), confirm);

            remindHandler.addReminder(new Reminder(targetDate,
                                                    message,
                                                    event.getAuthor()));
        } catch (Exception e) {
            rexCord.sendMessage(event.getChannel(),
                                "Could not interpret query :(");
        }
    }
}
