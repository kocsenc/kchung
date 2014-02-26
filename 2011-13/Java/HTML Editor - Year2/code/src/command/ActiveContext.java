package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.GUI.EditorFrame;
import TeamTwoHTMLEditor.GUI.TabFrame;

import javax.swing.*;

/**
 * Author:      Grant Kurtz
 * The ActiveContext class allows all of the Commands to access the different
 * areas of the EditorFrame such as the active tab and index through one object.
 */
public class ActiveContext {
    private final int index;
    private final JTextArea activeTextArea;
    private final EditorFrame parent;
    private final TabFrame tabFrame;
    private final EditorFrame.ListType SelectedListType;

    public ActiveContext(int index, JTextArea activeTextArea, EditorFrame parent, TabFrame tabFrame, EditorFrame.ListType type) {
        this.index = index;
        this.activeTextArea = activeTextArea;
        this.parent = parent;
        this.tabFrame = tabFrame;
        this.SelectedListType = type;
    }

	/**
	 * @return int - The index of the active pane.
	 */
    public int getIndex() {
        return index;
    }

	/**
	 * @return JTEXTArea - The active text area itself.
	 */
    public JTextArea getActiveTextArea() {
        return activeTextArea;
    }

	/**
	 * @return EditorFrame - The parent.
	 */
    public EditorFrame getParent() {
        return parent;
    }

	/**
	 * @return TabFrame - The frame of tabs.
	 */
    public TabFrame getTabFrame() {
        return tabFrame;
    }

	/**
	 * @return ListType - Either ALPHABETICAL or IN ORDER.
	 */
    public EditorFrame.ListType getListType() {
        return this.SelectedListType;
    }
}
