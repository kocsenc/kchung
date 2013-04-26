package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 3/21/13 Time: 10:41 PM
 */
public class NewFileCommand implements Command{

	private String filename;

	public NewFileCommand(String s){
		filename = s;
	}

	/**
	 * Calls the file manager to create the new file.
	 *
	 * @param c - Command distributor who has a reference access to the
	 *          FileManager
	 */
	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		c.getFileManager().createNewFile(filename);
		c.getFileManager().printStatus();
	}
}
