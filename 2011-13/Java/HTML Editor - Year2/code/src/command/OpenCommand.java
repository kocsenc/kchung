package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;
import TeamTwoHTMLEditor.GUI.TabFrame;

import javax.swing.*;
import java.io.File;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 3/21/13 Time: 9:39 PM Command
 * Class for opening a class. During the execute, the command adds a document
 * observer to be notified of this documents change
 */
public class OpenCommand implements Command{
	private File f;
	private final ActiveContext context;

	public OpenCommand(File openFile, ActiveContext context){
		f = openFile;
		this.context = context;
	}

	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		c.getFileManager().openFile(f, context.getActiveTextArea());
		context.getActiveTextArea().getDocument().addDocumentListener(
				c.getFileManager().getFileAt(context.getIndex()));
		cmd.openCommandExecuted(f.getPath(), false, c, context);
		c.getFileManager().printStatus();
	}

}
