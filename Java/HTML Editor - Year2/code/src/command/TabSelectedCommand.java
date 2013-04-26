package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;

import javax.swing.*;

/**
 * @author Keegan Parrotte - kmp3325@rit.edu
 * When text is highlighted, the user may press the TAB key and tab the selected
 * block of text rather than replace it with a TAB character.
 */
public class TabSelectedCommand implements Command{
	private String selectedText;
	private final ActiveContext context;

	public TabSelectedCommand(String selectedText, ActiveContext context){
		this.selectedText = selectedText;
		this.context = context;
	}

	/**
	 * This execute method is used when pressing the tab key when text is
	 * highlighted.
	 *
	 * @param c - Command distributor who has a reference access to the
	 *          FileManager
	 */
	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		String[] split = selectedText.split("\n");
		String newSelected = "";
		for(String s : split){
			newSelected += ("\t" + s + "\n");
		}
		// Substring gets rid of the first tab that is still
		// registered for the original tab keyPress and
		// the last extra newline
		context.getActiveTextArea().replaceSelection(newSelected.substring(1
				, newSelected.length() - 1));
	}


}
