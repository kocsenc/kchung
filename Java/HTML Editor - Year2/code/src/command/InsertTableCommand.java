package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 3/25/13 Time: 11:28 PM
 */
public class InsertTableCommand implements Command{

	int row;
	int col;
	private final ActiveContext context;

	public InsertTableCommand(int r, int c, ActiveContext context){
		row = r;
		col = c;
		this.context = context;
	}

	/**
	 * Same thing as the insert list command, but this has two loops to form the
	 * rows and columns appropriately
	 *
	 * @param c - Command distributor who has a reference access to the
	 *          FileManager
	 */
	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		cmd.createUndoState(c, context);
		StringBuilder listElement = new StringBuilder("");
		int tabCount =
				EditorFrame.getTabCount(EditorFrame.getCurrentLine(
						context.getActiveTextArea()));
		String tabs = EditorFrame.indentTabs(tabCount);
		System.out.println(tabCount);

		listElement.append("<table>\n");
		while(row-- > 0){
			listElement.append(tabs).append("\t<tr>\n");
			int rCol = col;
			while(rCol-- > 0){
				listElement.append(tabs).append("\t\t<td></td>\n");
			}
			listElement.append(tabs).append("\t</tr>\n");
		}
		listElement.append(tabs).append("</table>");
		context.getActiveTextArea().insert(listElement.toString(),
										   context.getActiveTextArea().getCaretPosition());
	}
}
