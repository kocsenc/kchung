package TeamTwoHTMLEditor.GUI;

import TeamTwoHTMLEditor.OutlineEditorKit.OutlineEditorKit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 4/6/13 Time: 3:49 PM To change
 * this template use File | Settings | File Templates.
 */
public class TabFrame extends JPanel {

    private boolean wordWrap;
    private int tabSize;
    private JScrollPane linkListScrollPane, editorScrollPane, outlineViewScrollPane;
    private JTextArea editorTextArea;
    private JEditorPane outlineTextArea;
    private JList linkList;
    private JPanel mainPanel;
    private boolean inOutline;


    /**
     * Constructor for the tab frame, which is a panel that is showed at every tab.
     *
     * @param editorFrame - The parent Editor Frame
     */

    public TabFrame(EditorFrame editorFrame) {
        this.wordWrap = editorFrame.getWordWrapBoolean();
        tabSize = editorFrame.getGlobalTabSize();
        initComponents();
        //By Default, the links view will be set to false
        linkListScrollPane.setVisible(true);

    }

    /**
     * Initializes the GUI components of the tabFrame.
     */
    private void initComponents() {
        inOutline = false;

        editorTextArea = setupTextArea();
        editorScrollPane = new JScrollPane(editorTextArea);

        linkList = new JList();
        linkListScrollPane = new JScrollPane(linkList);

        outlineTextArea = setupOutlineArea();
        outlineViewScrollPane = new JScrollPane(outlineTextArea);

        mainPanel = new JPanel();
        mainPanel.add(editorScrollPane, 0);
        mainPanel.add(outlineViewScrollPane, 1);
        mainPanel.setLayout(new CardLayout());


        GroupLayout layout = new GroupLayout(this);

        //Setting layout
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                .addComponent(linkListScrollPane))
                        .addContainerGap()));

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(linkListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));


        setLayout(layout);

        mainPanel.setMaximumSize(mainPanel.getPreferredSize());

    }


    /**
     * Helper function to set up a pane to be inserted by tab pane MUST always
     * be called when making a new representation of a file
     *
     * @return the JTextArea to be shown by the component of the tab pane
     */
    private JTextArea setupTextArea() {
        JTextArea newEditorPane = new JTextArea();
        newEditorPane.setLineWrap(wordWrap);
        newEditorPane.setTabSize(tabSize);
        return newEditorPane;
    }

    private JEditorPane setupOutlineArea() {
        JEditorPane outlinePreview = new JEditorPane();
        outlinePreview.setEditorKit(new OutlineEditorKit());
        outlinePreview.setText(editorTextArea.getText());
        outlinePreview.setEditable(false);
        return outlinePreview;
    }

    /**
     * Sets the list that is being shown given an array.
     * The method makes a new LinksListModel with the given array and then
     * updates the List View.
     *
     * @param array - The array that contains the string representation of links
     */
    public void setList(ArrayList array) {
        if (!array.isEmpty()) {
            linkList.setModel(new LinksListModel(array));
        } else {
            ArrayList<String> x = new ArrayList<String>(1);
            x.add("Empty");
            linkList.setModel(new LinksListModel(x));
        }

    }

    /**
     * Set if you want to see the link view visible or not.
     *
     * @param b
     */
    public void setLinkViewVisible(boolean b) {
        this.linkListScrollPane.setVisible(b);
    }

    /**
     * Returns the text pane from the TabFrame Object
     *
     * @return - The text area of the editor
     */
    public JTextArea getTextPane() {
        return this.editorTextArea;
    }

    /**
     * Switches the main view to the outline view.
     */
    public void toggleOutlineView() {
        if (!inOutline) {
            mainPanel.remove(1);

            outlineTextArea = setupOutlineArea();
            outlineViewScrollPane = new JScrollPane(outlineTextArea);

            //INCOMPATIBLE WITH jre <= 1.6 mainPanel.add(outlineViewScrollPane, 1);
            mainPanel.add(outlineViewScrollPane, "outlineScrollPane");

            ((CardLayout) mainPanel.getLayout()).next(mainPanel);
            outlineViewScrollPane.revalidate();
            mainPanel.revalidate();

            inOutline = true;
            this.revalidate();
        } else {
            ((CardLayout) mainPanel.getLayout()).next(mainPanel);
            mainPanel.revalidate();
            inOutline = false;
            this.revalidate();
        }
    }

    /**
     *
     * @return boolean wheather the window is in outline mode.
     */
    public boolean isInOutline() {
        return inOutline;
    }
}
