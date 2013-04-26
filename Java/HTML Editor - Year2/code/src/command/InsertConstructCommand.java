package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;

import javax.swing.*;

/**
 * The class that inserts all of the different Constructs.
 * Currently the supported constructs are H1, H2, H3, BOLD, and ITALICS
 */
public class InsertConstructCommand implements Command{

	public enum Construct{
		H1, H2, H3, BOLD, ITALICS
	}

	private Construct construct;
	private final ActiveContext context;


	public InsertConstructCommand(Construct cn, ActiveContext context){
		construct = cn;
		this.context = context;
	}

	/**
	 * The execute command just makes a new stringBuilder and appends the
	 * specific string given the Case of the enum. The enum depicts what type of
	 * header it is.
	 * <p/>
	 * After appending to the StringBuilder, it is inserted into the area and
	 * then the caret is set in between the tags so that the usr can start
	 * typing code between the tags.
	 *
	 * @param c - Command distributor who has a reference access to the
	 *          FileManager
	 */
	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		cmd.createUndoState(c, context);

		// If there is no active editor window, then do nothing
		if(context.getActiveTextArea() == null){
			return;
		}

		StringBuilder insertStr = new StringBuilder("");

		String input = null;
		switch(construct){
			case H1:
				input = "<h1></h1>";
				insertStr.append(input);
				break;
			case H2:
				input = "<h2></h2>";
				insertStr.append(input);
				break;
			case H3:
				input = "<h3></h3>";
				insertStr.append(input);
				break;

			case BOLD:
				input = "<b></b>";
				insertStr.append(input);
				break;

			case ITALICS:
				input = "<i></i>";
				insertStr.append(input);
				break;

		}

		context.getActiveTextArea().insert(insertStr.toString(), context.getActiveTextArea().getCaretPosition());
		context.getActiveTextArea().setCaretPosition(
				context.getActiveTextArea().getCaretPosition() - (input.length() / 2) - 1);

	}
}
