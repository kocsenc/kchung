package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * Author:      Grant Kurtz Class that runs the auto indent
 */
public class AutoIndentCommand implements Command{

	private final ActiveContext context;

	public AutoIndentCommand(ActiveContext context){
		this.context = context;
	}

	/**
	 * Executing this command forces the active text area to auto-indent with each newline.
	 * @param c - Command distributor who has a reference access to the FileManager.
	 * @param cmd - Command Mediator references other commands as necessary.
	 */
	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		String prevLine = EditorFrame.getPreviousLine(context.getActiveTextArea());
		int numTabs = EditorFrame.getTabCount(prevLine);
		context.getActiveTextArea().insert(EditorFrame.indentTabs(numTabs), context.getActiveTextArea().getCaretPosition());
	}
}
