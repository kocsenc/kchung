package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * The undo command accesses the memento in order to revert to the last state.
 */
public class UndoCommand implements Command{

	private final ActiveContext context;

	public UndoCommand(ActiveContext context){
		this.context = context;
	}

	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		c.getFileManager().undoChange(context.getIndex());
		context.getActiveTextArea().setText(c.getFileManager().getContentsOf(context.getIndex()));
	}
}
