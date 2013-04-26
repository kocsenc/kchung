package TeamTwoHTMLEditor;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 3/22/13 Time: 2:58 PM
 * Distributor/Controller class - Contains a reference to FileManager
 */
public class CommandDistributor{
	private FileManager fileManager;


	public CommandDistributor(FileManager fManager){
		fileManager = fManager;
	}

	public FileManager getFileManager(){
		return fileManager;
	}

}
