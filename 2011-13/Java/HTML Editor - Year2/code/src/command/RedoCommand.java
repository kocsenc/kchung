package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;

/**
 * Author:      Grant Kurtz
 * Command for undoing an undo......
 */
public class RedoCommand implements Command{
	private final ActiveContext context;

	public RedoCommand(ActiveContext context){
		this.context = context;
	}

	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		c.getFileManager().redoChange(context.getIndex());
		context.getActiveTextArea().setText(c.getFileManager().getContentsOf(context.getIndex()));
	}
}
