package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;
import java.io.File;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 3/25/13 Time: 3:38 PM Command
 * for Saving As. the execute method simply runs a save file given a path from
 * the file chooser
 */
public class SaveAsCommand implements Command{
	private File f;
	private final ActiveContext context;

	public SaveAsCommand(File saveFile, ActiveContext context){
		f = saveFile;
		this.context = context;
	}

	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		String contents = context.getActiveTextArea().getText();
		c.getFileManager().saveNewFile(f, contents, context.getIndex());
		cmd.saveAsCommandExecuted(f.getPath(), false, c, context);
		c.getFileManager().printStatus();
	}
}
