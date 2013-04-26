package TeamTwoHTMLEditor.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 4/14/13
 * Time: 7:27 PM
 * This is the small component on the tabs.
 * It let's there be a button in order to close the tab along with the ability to change the title.
 */
public class CloseTabComponent extends JPanel {
    JLabel titleLabel;
    JButton closeButton;

    public CloseTabComponent(String n) {
        super(new GridBagLayout());
        setOpaque(false);
        titleLabel = new JLabel(n);
        closeButton = new JButton("x");
        GridBagConstraints layout = new GridBagConstraints();
        layout.gridx = 0;
        layout.gridy = 0;
        layout.weightx = 1;
        add(titleLabel, layout);
        layout.gridx++;
        layout.weightx = 0;
        add(closeButton, layout);
        setName(n);
    }

    /**
     * This sets the title of the tab. Happens when there is a new file and is saved with a new name.
     *
     * @param title - The new title to set.
     */
    public void setLabelTitle(String title) {
        titleLabel.setText(title);
    }

    /**
     * This method returns the closing button that is used to add the action performed in EditorFrame.java.
     *
     * @return - The X button to add a button listener in the EditorFrame
     */
    public JButton getCloseButton() {
        return closeButton;
    }

}
