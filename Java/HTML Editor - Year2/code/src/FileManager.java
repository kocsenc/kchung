package TeamTwoHTMLEditor;

import TeamTwoHTMLEditor.GUI.TabFrame;
import TeamTwoHTMLEditor.Links.Links;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.html.HTML;
import java.io.File;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 3/21/13
 * Time: 10:00 AM
 * This is the model for the editor
 */
public class FileManager {
    private ArrayList<HTMLFile> HTMLFileArray;
    private static int numOpenFiles;

    public FileManager() {
        HTMLFileArray = new ArrayList<HTMLFile>();
        numOpenFiles = HTMLFileArray.size();
    }


    /**
     *
     */
    public void createNewFile(String filename) {
        HTMLFile x = new HTMLFile(filename, false);
        HTMLFileArray.add(x);
        numOpenFiles = HTMLFileArray.size();
        System.out.println("New File was Created with name: " + filename + "\nTotal files: " + Integer.toString(numOpenFiles));
    }

    /**
     *
     */
    public void openFile(File f, JTextArea pane) {
        HTMLFile x = new HTMLFile(f.getPath());
        x.setNeedSaveAs(false);
        x.setNeedToSave(false);
        HTMLFileArray.add(x);
        numOpenFiles = HTMLFileArray.size();
        printStatus();
        String contents = x.getFileContents();
        pane.setText(contents);
    }

    public Links getLinksAt(int index) {
        return HTMLFileArray.get(index).getLinksList();
    }

    /**
     * @param index - the index of where to close the file (backend)
     */
    public void closeFile(int index) {
        HTMLFileArray.remove(index);
        numOpenFiles = HTMLFileArray.size();

    }

    /**
     *
     */
    public void saveFile(File f, String contents, int indexOfFile) {
		HTMLFileArray.get(indexOfFile).setFileContents(contents);
        HTMLFileArray.get(indexOfFile).setNeedToSave(false);
        HTMLFileArray.get(indexOfFile).setNeedSaveAs(false);
		HTMLFileArray.get(indexOfFile).saveFile();
    }

    public void quickSaveFile(String path, String contents, int indexOfFile) {
        System.out.println("Quick Saved file: " + path);
		HTMLFileArray.get(indexOfFile).setFileContents(contents);
        HTMLFileArray.get(indexOfFile).setNeedToSave(false);
        HTMLFileArray.get(indexOfFile).setNeedSaveAs(false);
		HTMLFileArray.get(indexOfFile).saveFile();
    }


    /**
     * Runs through the array of files to check if they have all been saved
     *
     * @return true if the system is allowed to quit
     */
    public boolean canQuit() {
        if (HTMLFileArray.isEmpty()) {
            return true;
        }
        for (HTMLFile aHTMLFileArray : HTMLFileArray) {
            if (aHTMLFileArray.isNeedToSave()) {
                return false;
            }
        }
        return true;
    }

    public boolean canQuitAt(int index) {
        return !(HTMLFileArray.get(index).isNeedToSave());
    }


    public void printStatus() {
        System.out.println("**** File Manager Status *****");
        System.out.println("Total number of files open: " +
                Integer.toString(HTMLFileArray.size()));
        System.out.println("File List:");
        if (HTMLFileArray.isEmpty()) System.out.println(" NONE");
        for (HTMLFile aFile : HTMLFileArray) {
            System.out.println("\t" + aFile.getName());
            if (aFile.isNeedToSave()) {
                System.out.println("\t\tNEEDS SAVE");
            } else {
                System.out.println("\t\tSAVED");
            }

        }
        System.out.println("******************************END");
    }

    /**
     * Method to see if a file needs the SaveAs dialog.
     *
     * @param index - the index of where in the tabs and where in the file array the file to deal with is
     * @return - True if the file needs a saveAs, false otherwise
     */
    public boolean needsSaveAsDialog(int index) {
        return HTMLFileArray.get(index).getNeedSaveAs();
    }

    /**
     * Get the name (which is the path) of the file requested
     *
     * @param index - The file requested at this index
     * @return - Returns a string of the filename == pathname
     */
    public String getPathAt(int index) {
        return HTMLFileArray.get(index).getFilename();
    }

    /**
     * This is in order for every pane that is in the view to have the actual back end file object (HTMLFile instance)
     * Listen to it for changes.
     *
     * @param activePaneIndex - the index of the location of the file
     * @return The document (HTML Document extend DocumentListener)
     */
    public DocumentListener getFileAt(int activePaneIndex) {
        return HTMLFileArray.get(activePaneIndex);
    }

	public void undoChange(int index){
		HTMLFileArray.get(index).restoreState();
	}

	public void createUndoState(int index){
		HTMLFileArray.get(index).createMemento();
	}

	public void saveNewFile(File f, String contents, int index){
		HTMLFileArray.remove(index);
		HTMLFileArray.add(index, new HTMLFile(f.getAbsolutePath(), false));
		HTMLFileArray.get(index).setNeedSaveAs(false);
		HTMLFile file = HTMLFileArray.get(index);
		file.saveFile(contents);
	}

	public String getContentsOf(int index){
		return HTMLFileArray.get(index).getFileContents();
	}

	public void updateFileContents(String contents, int index){
		HTMLFileArray.get(index).setFileContents(contents);
	}

	public void redoChange(int index){
		HTMLFileArray.get(index).restoreNextState();
	}
}
