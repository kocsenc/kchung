package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;

/**
 * Created with IntelliJ IDEA. User: Shannon Date: 4/6/13 Time: 11:28 PM
 * Command to insert HREF.
 */
public class InsertATagCommand implements Command {
    private final ActiveContext context;
    private String href;
    private String name;

    public InsertATagCommand(String href, String name, ActiveContext context) {
        this.href = href;
        this.name = name;
        this.context = context;
    }

    public void execute(CommandDistributor c, CommandMediator cmd) {
		cmd.createUndoState(c, context);
        String input;

        // If there is no active editor window, then do nothing
        if (context.getActiveTextArea() == null) {
            return;
        }

        input = "<a href=\"" + href + "\">" + name + "</a>";
        StringBuilder insertStr = new StringBuilder("");
        insertStr.append(input);

        context.getActiveTextArea().insert(insertStr.toString(),
                context.getActiveTextArea().getCaretPosition());

        cmd.insertCommandExecuted(context, c);
    }
}

