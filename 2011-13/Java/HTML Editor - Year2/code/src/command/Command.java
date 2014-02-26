package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 3/21/13 Time: 9:37 PM Command
 * Interface that is implemented by all the commands Only requires parameter
 * Command Distributor
 */
public interface Command{

	/**
	 * @param c - Command distributor who has a reference access to the
	 *          FileManager
	 */
	void execute(CommandDistributor c, CommandMediator cmd);


}
