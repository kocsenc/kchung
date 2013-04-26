package TeamTwoHTMLEditor.command;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 4/5/13
 * Time: 3:31 PM
 * Command to insert an image. Gets info from popped up gui and then inserts the string into the active pane.
 */

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;

import javax.swing.*;

public class InsertImageCommand implements Command {

    private String src;
	private final ActiveContext context;

	public InsertImageCommand(String src, ActiveContext context) {
        this.src = src;
		this.context = context;
    }

    public void execute(CommandDistributor c, CommandMediator cmd) {
		cmd.createUndoState(c, context);
        String input;

        // If there is no active editor window, then do nothing
        if (context.getActiveTextArea() == null) {
            return;
        }
        input = "<img src=\"" + src + "\"></img>";
        StringBuilder insertStr = new StringBuilder("");
        insertStr.append(input);

		context.getActiveTextArea().insert(insertStr.toString(), context.getActiveTextArea().getCaretPosition());
    }
}
