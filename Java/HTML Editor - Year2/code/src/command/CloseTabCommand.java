package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * @author Keegan Parrotte - kmp3325@rit.edu Class that handles the closing of a
 *         tab and an implicit file
 */
public class CloseTabCommand implements Command{

	private final ActiveContext context;

	public CloseTabCommand(ActiveContext context){
		this.context = context;
	}

	/**
	 * This is the execute command and it checks whether you can close a file
	 * (which is simply if the file has been saved) And then if you can close
	 * the file then it will close the tab and then call FileManager to close
	 * the file. Otherwise, if the file is not saved, an option pane is shown
	 * from the editor frame, in which the user can chose to close the file or
	 * to interrupt the close to save manually.
	 *
	 * @param c - Command distributor who has a reference access to the
	 *          FileManager
	 */
	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		if(c.getFileManager().canQuitAt(context.getIndex())){
			context.getParent().closeTab();
			c.getFileManager().closeFile(context.getIndex());
		}
		else{
			int n = JOptionPane.showConfirmDialog(
					context.getParent(),
					"This Tab has not been saved, would you like to quit anyway?",
					"Unsaved Tab",
					JOptionPane.YES_NO_OPTION);
			if(n == JOptionPane.YES_OPTION){
				context.getParent().closeTab();
				c.getFileManager().closeFile(context.getIndex());
			}
			else{

			}
		}

		c.getFileManager().printStatus();
	}

}
