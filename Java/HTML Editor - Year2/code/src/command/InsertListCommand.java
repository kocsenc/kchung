package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;

/**
 * For inserting List elements.
 */
public class InsertListCommand implements Command{

	public enum ListType{NUMBERED, BULLETED, DICTIONARY}
	int size;
	ListType type;
	private final ActiveContext context;


	public InsertListCommand(ListType l, int size, ActiveContext context){
		this.size = size;
		type = l;
		this.context = context;
	}

	/**
	 * The execute command checks what type of list has to be inserted by checking
	 * the enums by case. And then a loop iterates for how ever many times thes
	 * user has inputed from the Dialog shown. The appropriate list is then posted
	 * on the view.
	 *
	 * @param c - Command distributor who has a reference access to the
	 *          FileManager
	 */
	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		cmd.createUndoState(c, context);
		StringBuilder listElement = new StringBuilder("");
		int tabCount =
				EditorFrame.getTabCount(EditorFrame.getCurrentLine(context.getActiveTextArea()));
		String tabs = EditorFrame.indentTabs(tabCount);
		System.out.println(tabCount);

		switch(type){
			case NUMBERED:
				listElement.append("<ol>\n");
				while(size-- > 0){
					listElement.append(tabs).append("\t<li></li>\n");
				}
				listElement.append(tabs).append("</ol>");
				break;

			case BULLETED:
				listElement.append("<ul>\n");
				while(size-- > 0){
					listElement.append(tabs).append("\t<li></li>\n");
				}
				listElement.append(tabs).append("</ul>");
				break;

			case DICTIONARY:
				listElement.append("<dl>\n");
				while(size-- > 0){
					listElement.append(tabs).append("\t<dt></dt>\n");
					listElement.append(tabs).append("\t<dd></dd>\n");
				}
				listElement.append(tabs).append("</dl>");
				break;
		}
		System.out.println(listElement.toString());
		context.getActiveTextArea().insert(listElement.toString(),
										   context.getActiveTextArea().getCaretPosition());
	}

}
