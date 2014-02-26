package TeamTwoHTMLEditor.GUI;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.GUI.inputDialogs.*;
import TeamTwoHTMLEditor.command.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen Chung
 * Date: 3/17/13
 * Time: 7:48 PM
 * This is the main frame for the editor.  Will be the class that launches (invokes) only once.
 */

public class EditorFrame extends JFrame {
    private CommandDistributor commandDistributor;
    private CommandMediator commandMediator;
    private JFileChooser fc;
    private int newFileCount = 1;
    private int activePaneIndex = 0;
    private int globalTabSize = 4;
    private JTabbedPane tabPane;
    private ListType SelectedListType;

    public enum ListType {
        ALPHABETICAL, INORDER
    }

    private ArrayList<TabFrame> tabFrames;

    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, menuInsert, menuOptions, menuHTML, menuAbout;
    private JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, closeTabMenuItem, validateMenuItem, quitMenuItem, tabWidthMenuItem, aboutUsMenuItem, helpMenuItem;
    private JMenuItem pasteMenuItem, copyMenuItem, undoMenuItem, redoMenuItem;
    private JMenuItem renderPreviewMenuItem, refreshLinksMenuItem, toggleOutlineMenuItem;
    private JCheckBoxMenuItem toggleWordWrapMenuItem, toggleAutoIndentMenuItem,
            toggleLinksViewMenuItem;
    private ButtonGroup radioButtonGroup = new ButtonGroup();
    private JRadioButtonMenuItem alphabeticalRadioButton;
    private JRadioButtonMenuItem inOrderRadioButton;


    // headers, font emphasis (bold, italics), list (numbered, bulleted,
    // dictionary), tables.
    private JMenu insertHeaderMenu;
    private JMenuItem insertH1MenuItem, insertH2MenuItem, insertH3MenuItem,
            insertTableMenuItem, insertImageMenuItem, insertATagMenuItem;
    private JMenuItem boldMenuItem, italicsMenuItem;
    private JMenuItem insertNumberList, insertBulletList, insertDictionaryList;
    private JMenu fontEmphasisMenu, insertListMenu;


    public EditorFrame(CommandDistributor cdis, CommandMediator cmdm) {
        initComponents();
        commandDistributor = cdis;
        commandMediator = cmdm;
        fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("HTML Document", "html", "htm"));
        tabFrames = new ArrayList<TabFrame>();

    }


    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("IntelliHTML - An HTML FileManager from T2");
        setBounds(new Rectangle(0, 0, 500, 530));
        setName("EditorFrame");
        setResizable(true);
        this.setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        tabPane = new JTabbedPane();
        tabPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeTabFocus();
                if (tabPane.getTabCount() <= 0) {
                    setEverythingFileDependantEnabled(false);
                } else {
                    setEverythingFileDependantEnabled(true);
                }
            }
        });

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Creating all Menu's
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuAbout = new JMenu("About");
        menuInsert = new JMenu("Insert");
        menuOptions = new JMenu("Options");
        menuHTML = new JMenu("HTML");


        //Build the first menu - File    ************** BEGIN ************************** //
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuFile.getAccessibleContext().setAccessibleDescription("This menu allows you to do all of the file handling.");

        ///Adding File MenuItems
        newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
        openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
        saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveAsMenuItem = new JMenuItem("Save As", KeyEvent.VK_A);
        validateMenuItem = new JMenuItem("Validate", KeyEvent.VK_V);
        closeTabMenuItem = new JMenuItem("Close Tab", KeyEvent.VK_W);
        quitMenuItem = new JMenuItem("Quit", KeyEvent.VK_F4);

        ///Adding Shortcuts to MenuItems
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        validateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
        closeTabMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));

        //Adding Icons

        URL newMenuItemURL = getClass().getResource("/stock_new.png");
        URL openMenuItemURL = getClass().getResource("/stock_open.png");
        URL saveMenuItemURL = getClass().getResource("/stock_data_save.png");
        URL saveAsMenuItemURL = getClass().getResource("/stock_save_template.png");
        URL validateURL = getClass().getResource("/ok.png");
        URL closeTabURL = getClass().getResource("/stock_data_delete_table.png");

        if (newMenuItemURL != null) newMenuItem.setIcon(new ImageIcon(newMenuItemURL));
        if (openMenuItemURL != null) openMenuItem.setIcon(new ImageIcon(openMenuItemURL));
        if (saveMenuItemURL != null) if (newMenuItemURL != null) saveMenuItem.setIcon(new ImageIcon(saveMenuItemURL));
        if (saveAsMenuItemURL != null) saveAsMenuItem.setIcon(new ImageIcon(saveAsMenuItemURL));
        if (validateURL != null) validateMenuItem.setIcon(new ImageIcon(validateURL));
        if (closeTabURL != null) closeTabMenuItem.setIcon(new ImageIcon(closeTabURL));


        ///Adding listeners
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newMenuItemActionPerformed();
            }
        });

        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMenuItemActionPerformed();
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMenuItemActionPerformed();
            }
        });

        saveAsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAsMenuItemActionPerformed();
            }
        });

        validateMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateActionPerformed();
            }
        });

        closeTabMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeTabMenuItemActionPerformed();
            }
        });

        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitMenuItemActionPerformed();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quitMenuItemActionPerformed();
            }
        });

        menuFile.add(newMenuItem);
        menuFile.add(openMenuItem);
        menuFile.add(saveMenuItem);
        menuFile.add(saveAsMenuItem);
        menuFile.add(validateMenuItem);
        menuFile.add(closeTabMenuItem);
        menuFile.add(quitMenuItem);
        menuBar.add(menuFile);

        // BUILD FILE ************************************END****************************//


        //Build the second menu - Edit   ************** BEGIN ************************** //
        menuEdit.setMnemonic(KeyEvent.VK_E);
        menuEdit.getAccessibleContext().setAccessibleDescription("This menu allows you to edit the file content");

        ///Adding Edit MenuItems
        copyMenuItem = new JMenuItem("Copy", KeyEvent.VK_C);
        pasteMenuItem = new JMenuItem("Paste", KeyEvent.VK_V);
        undoMenuItem = new JMenuItem("Undo", KeyEvent.VK_U);
        redoMenuItem = new JMenuItem("Redo", KeyEvent.VK_R);

        ///Shortcuts
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        undoMenuItem.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK)));
        redoMenuItem.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK)));

        //Adding Icons
        URL copyMenuItemURL = getClass().getResource("/edit_copy.png");
        URL pasteMenuItemURL = getClass().getResource("/stock_paste.png");
        URL undoMenuItemURL = getClass().getResource("/stock_undo.png");
        URL redoMenuItemURL = getClass().getResource("/stock_redo.png");

        if (copyMenuItemURL != null) copyMenuItem.setIcon(new ImageIcon(copyMenuItemURL));
        if (pasteMenuItemURL != null) pasteMenuItem.setIcon(new ImageIcon(pasteMenuItemURL));
        if (undoMenuItemURL != null) undoMenuItem.setIcon(new ImageIcon(undoMenuItemURL));
        if (redoMenuItemURL != null) redoMenuItem.setIcon(new ImageIcon(redoMenuItemURL));


        ///Listeners for Menu Items
        copyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyMenuItemActionPerformed();
            }
        });

        pasteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pasteMenuItemActionPerformed();
            }
        });

        undoMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoMenuItemActionPerformed();
            }
        });

        redoMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redoMenuItemActionPerformed();
            }
        });


        menuEdit.add(copyMenuItem);
        menuEdit.add(pasteMenuItem);
        menuEdit.addSeparator();
        menuEdit.add(undoMenuItem);
        menuEdit.add(redoMenuItem);

        menuBar.add(menuEdit);
        // BUILD EDIT **************************************END**************************//


        //Build the Insert Menu *********************** BEGIN ************************ //

        menuInsert.setMnemonic(KeyEvent.VK_I);
        menuInsert.getAccessibleContext().setAccessibleDescription("Allows you to insert HTML tags");

        /// Adding SubMenus for Headers and MenuItems
        insertHeaderMenu = new JMenu("Headers");
        insertH1MenuItem = new JMenuItem("H1", KeyEvent.VK_H);
        insertH2MenuItem = new JMenuItem("H2", KeyEvent.VK_H);
        insertH3MenuItem = new JMenuItem("H3", KeyEvent.VK_H);
        insertTableMenuItem = new JMenuItem("Table", KeyEvent.VK_T);
        insertImageMenuItem = new JMenuItem("Image");
        insertATagMenuItem = new JMenuItem("Link");


        //// Adding action listeners for MenuItems above
        insertH1MenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertH1ActionPerformed();
            }
        });
        insertH2MenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertH2ActionPerformed();
            }
        });
        insertH3MenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertH3ActionPerformed();
            }
        });

        insertTableMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertTableActionPerformed();
            }
        });

        insertImageMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertImageActionPerformed();
            }
        });
        insertATagMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertATagActionPerformed();
            }
        });

        /// Adding submenus and menu items
        fontEmphasisMenu = new JMenu("Font Emphasis");
        boldMenuItem = new JMenuItem("Bold", KeyEvent.VK_B);
        italicsMenuItem = new JMenuItem("Italics", KeyEvent.VK_I);
        fontEmphasisMenu.add(boldMenuItem);
        fontEmphasisMenu.add(italicsMenuItem);

        insertListMenu = new JMenu("List");
        insertNumberList = new JMenuItem("Number List");
        insertDictionaryList = new JMenuItem("Dictionary List");
        insertBulletList = new JMenuItem("Bullet List");
        insertListMenu.add(insertNumberList);
        insertListMenu.add(insertDictionaryList);
        insertListMenu.add(insertBulletList);

        //// Adding action listeners to menu items above
        boldMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fontEmphasisActionPerformed(e);
            }
        });

        italicsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fontEmphasisActionPerformed(e);
            }
        });

        insertNumberList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertListActionPerformed(e);
            }
        });
        insertDictionaryList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertListActionPerformed(e);
            }
        });

        insertBulletList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertListActionPerformed(e);
            }
        });


        menuInsert.add(insertHeaderMenu);
        menuInsert.add(insertTableMenuItem);
        menuInsert.add(fontEmphasisMenu);
        menuInsert.add(insertListMenu);
        menuInsert.add(insertImageMenuItem);
        menuInsert.add(insertATagMenuItem);

        //Adding icons
        insertHeaderMenu.setIcon(new ImageIcon(getClass().getResource("/stock_edit_headers_and_footers.png")));
        insertTableMenuItem.setIcon(new ImageIcon(getClass().getResource("/show_table_row.png")));
        insertImageMenuItem.setIcon(new ImageIcon(getClass().getResource("/stock_insert_image.png")));
        fontEmphasisMenu.setIcon(new ImageIcon(getClass().getResource("/stock_font.png")));
        boldMenuItem.setIcon(new ImageIcon(getClass().getResource("/format_text_bold.png")));
        italicsMenuItem.setIcon(new ImageIcon(getClass().getResource("/format_text_italic.png")));
        insertATagMenuItem.setIcon(new ImageIcon(getClass().getResource("/insert_link.png")));
        insertListMenu.setIcon(new ImageIcon(getClass().getResource("/stock_list.png")));
        insertNumberList.setIcon(new ImageIcon(getClass().getResource("/stock_list_number.png")));
        insertBulletList.setIcon(new ImageIcon(getClass().getResource("/stock_list_bullet.png")));
        insertDictionaryList.setIcon(new ImageIcon(getClass().getResource("/stock_list_insert_unnumbered.png")));


        insertHeaderMenu.add(insertH1MenuItem);
        insertHeaderMenu.add(insertH2MenuItem);
        insertHeaderMenu.add(insertH3MenuItem);
        /// Handling events


        menuBar.add(menuInsert);
        // ********************************** END ************************************//

        //Build the third menu - Options *****************BEGIN********************//
        menuOptions.setMnemonic(KeyEvent.VK_O);

        ///Adding menuItem
        tabWidthMenuItem = new JMenuItem("Tab Width");
        toggleWordWrapMenuItem = new JCheckBoxMenuItem("Wrap Text", true);
        toggleAutoIndentMenuItem = new JCheckBoxMenuItem("Auto Indent", true);
        toggleLinksViewMenuItem = new JCheckBoxMenuItem("View Links", true);
        alphabeticalRadioButton = new JRadioButtonMenuItem("Alphabetical", false);
        inOrderRadioButton = new JRadioButtonMenuItem("In Order", true);
        radioButtonGroup.add(alphabeticalRadioButton);
        radioButtonGroup.add(inOrderRadioButton);
        this.SelectedListType = ListType.INORDER;

        URL tabIconURL = getClass().getResource("/stock_table_fixed_proportional.png");
        if (tabIconURL != null) tabWidthMenuItem.setIcon(new ImageIcon(tabIconURL));

        tabWidthMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabWidthActionPerformed();
            }
        });

        toggleWordWrapMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleWordWrapActionPerformed();
            }
        });

        toggleLinksViewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleLinksViewActionPerformed();
            }
        });

        alphabeticalRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alphabeticalRadioButtonActionPerformed();
            }
        });

        inOrderRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inOrderRadioButtonActionPerformed();
            }
        });


        menuOptions.add(tabWidthMenuItem);
        menuOptions.add(toggleWordWrapMenuItem);
        menuOptions.add(toggleAutoIndentMenuItem);
        menuOptions.add(toggleLinksViewMenuItem);
        menuOptions.addSeparator();
        menuOptions.add(new JLabel("Links View Options"));
        menuOptions.add(alphabeticalRadioButton);
        menuOptions.add(inOrderRadioButton);

        menuBar.add(menuOptions);
        // ********************************** END ************************************//

        //Build the fourth menu - HTML ************** BEGIN ************************** //
        menuHTML.setMnemonic(KeyEvent.VK_H);

        //adding Menu Items
        renderPreviewMenuItem = new JMenuItem("Preview");
        refreshLinksMenuItem = new JMenuItem("Refresh Links");
        toggleOutlineMenuItem = new JMenuItem("Toggle Outline View", KeyEvent.VK_E);

        renderPreviewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        refreshLinksMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        toggleOutlineMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, ActionEvent.CTRL_MASK));

        //Linking icons
        URL renderURL = getClass().getResource("/stock_preview.png");
        URL refreshURl = getClass().getResource("/stock_refresh.png");
        URL toggleURL = getClass().getResource("/stock_goto_outline.png");

        if (renderURL != null) renderPreviewMenuItem.setIcon(new ImageIcon(renderURL));
        if (refreshURl != null) refreshLinksMenuItem.setIcon(new ImageIcon(refreshURl));
        if (toggleURL != null) toggleOutlineMenuItem.setIcon(new ImageIcon(toggleURL));

        menuHTML.add(renderPreviewMenuItem);
        menuHTML.add(refreshLinksMenuItem);
        menuHTML.addSeparator();
        menuHTML.add(toggleOutlineMenuItem);

        renderPreviewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderActionPerformed();
            }
        });

        refreshLinksMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshLinksMenuItemActionPerformed();
            }
        });

        toggleOutlineMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleOutlineActionPerformed();
            }
        });

        menuBar.add(menuHTML);
        // ********************************** END ************************************//

        //Build the fifth menu - About ************** BEGIN ************************** //
        menuAbout.setMnemonic(KeyEvent.VK_A);

        // Adding About MenuItems
        aboutUsMenuItem = new JMenuItem("About the Authors");
        helpMenuItem = new JMenuItem("Help");

        //Adding icons
        aboutUsMenuItem.setIcon(new ImageIcon(getClass().getResource("/stock_about.png")));
        helpMenuItem.setIcon(new ImageIcon(getClass().getResource("/stock_help.png")));

        menuAbout.add(aboutUsMenuItem);
        menuAbout.add(helpMenuItem);

        helpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(helpMenuItem, "To start, open a" +
                        " file or create a new file through the File menu.\nTo" +
                        " contact the authors choose the About Authors option " +
                        "in the About menu.");
            }
        });

        aboutUsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(aboutUsMenuItem, "We are the great " +
                        "authors of this HTML editor:\nKocsen Chung - kxc4519@r" +
                        "it.edu\nKeegan Parrotte - kmp3325@rit.edu\nGrant Kurts - " +
                        "grk2929@rit.edu\nShannon Trudeau - smt9020@rit.edu\nCalvin " +
                        "DRosario - cbd2562@rit.edu");
            }
        });

        menuBar.add(menuAbout);

        setJMenuBar(menuBar);
        add(tabPane, BorderLayout.CENTER);
        if (tabPane.getTabCount() <= 0) {
            setEverythingFileDependantEnabled(false);
        }
    }
    // BUILD ABOUT **************************************END**************************//


    /**
     * This method is called whenever there are no files open. This is because many of the menu items cannot be enabled
     * if there is no file open.
     *
     * @param b - Boolean to set enabled to all the items that depend on open file
     */
    private void setEverythingFileDependantEnabled(boolean b) {
        closeTabMenuItem.setEnabled(b);
        saveMenuItem.setEnabled(b);
        saveAsMenuItem.setEnabled(b);
        validateMenuItem.setEnabled(b);
        menuInsert.setEnabled(b);
        menuHTML.setEnabled(b);
        menuEdit.setEnabled(b);

    }

    // ********************** Action Performed for File > X *****************************//
    //What to do when they  click New in File Menu
    private void newMenuItemActionPerformed() {
        new NewFileCommand("File" + Integer.toString(newFileCount)).execute(commandDistributor, commandMediator);
        JTextArea textArea = realizeNewTab("File" + newFileCount);
        //PRECONDITION FOR THIS: ADD FILE IN BACKEND + ADD TAB
        addListeners(textArea);   //KEY EVENT 4 : Add the listeners (document and other)
        newFileCount++;


    }

    //What to do when they click Open in File Menu
    private void openMenuItemActionPerformed() {
        int status = fc.showOpenDialog(this);
        if (status == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            JTextArea pane = realizeNewTab(f.getName());
            new OpenCommand(f, getActiveContext()).execute(commandDistributor, commandMediator);
            //PRECONDITION FOR THIS: ADD FILE IN BACKEND + ADD TAB
            addListeners(pane);                         //KEY EVENT 4 : Add the listeners (document and other)
        }
    }

    /**
     * Simply opens the file without the file chooser.
     *
     * @param f - The file to open
     */
    public void openFileWithoutFileChooser(File f) {
        JTextArea textArea = realizeNewTab(f.getName());
        new OpenCommand(f, getActiveContext()).execute(commandDistributor, commandMediator);
        addListeners(textArea);
    }

    //Creates a new tab and runs through each of the key events that are necessary
    //for setup.  It then associates a close tab button with each new tab.
    private JTextArea realizeNewTab(String name) {
        TabFrame newTabFrame = new TabFrame(this); //KEY EVENT 1 : Make new Tab Frame
        JTextArea pane = newTabFrame.getTextPane();
        tabFrames.add(newTabFrame);                //KEY EVENT 2 : Add the TabFrame to the Array

        tabPane.addTab(name, newTabFrame);  //KEY EVENT 3 : Add to tab view
        int index = tabPane.getTabCount() - 1;
        tabPane.setSelectedIndex(index);

        //For the close button on tabs, create JPanel with label for name of
        CloseTabComponent closePanel = new CloseTabComponent(name);
        tabPane.setTabComponentAt(index, closePanel);
        closePanel.getCloseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                JPanel p = (JPanel) b.getParent();
                int i = tabPane.indexOfTabComponent(p);
                tabPane.setSelectedIndex(i);
                closeTabMenuItemActionPerformed();
            }
        });
        return pane;
    }


    //What to do when a tab is selected
    private void changeTabFocus() {
        activePaneIndex = tabPane.getSelectedIndex();
    }

    //What to do when they click Save in File Menu
    private void saveMenuItemActionPerformed() {
        int i = tabPane.getSelectedIndex();
        if (!commandDistributor.getFileManager().needsSaveAsDialog(i)) {
            new SaveCommand(getActiveContext()).execute(commandDistributor, commandMediator);
        } else {
            saveAsMenuItemActionPerformed();
        }

    }

    //What to do when they click Save As in file menu
    private void saveAsMenuItemActionPerformed() {
        int status = fc.showSaveDialog(this);
        if (status == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            new SaveAsCommand(f, getActiveContext()).execute(commandDistributor, commandMediator);
            CloseTabComponent x = (CloseTabComponent) tabPane.getTabComponentAt(tabPane.getSelectedIndex());
            x.setLabelTitle(fc.getName(f));
        }

    }

    //What to do when clicking Validate in File MenuF
    private void validateActionPerformed() {
        new ValidateCommand(commandDistributor.getFileManager().getPathAt(activePaneIndex), true, getActiveContext()).execute(commandDistributor, commandMediator);
    }


    /**
     * Call the CloseTab command which goes through all the checks that must happen before closing a file/tab
     */
    private void closeTabMenuItemActionPerformed() {
        new CloseTabCommand(getActiveContext()).execute(commandDistributor, commandMediator);

    }

    /*
    Precondition: Have the file removed from the back end
    This method simply closes the tab on the view.
     */
    public void closeTab() {
        int index = activePaneIndex;
        tabPane.remove(index);
        tabFrames.remove(index);

    }

    //What to do when they click on Quit in File Menu
    private void quitMenuItemActionPerformed() {
        new ShutDownCommand(getActiveContext()).execute(commandDistributor, commandMediator);
    }

    // ******************************************** END ********************************//


    //********************** Action Performed for Edit > X *****************************//
    //What to do when copy or paste
    private void copyMenuItemActionPerformed() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
        }
    }

    private void pasteMenuItemActionPerformed() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
        }
    }

    //Undo Menu and redo action performed
    private void undoMenuItemActionPerformed() {
        new UndoCommand(getActiveContext()).execute(commandDistributor, commandMediator);
    }

    private void redoMenuItemActionPerformed() {
        new RedoCommand(getActiveContext()).execute(commandDistributor, commandMediator);
    }

    //******************************** END *******************************************//

    //********************** Action Performed for Insert > X *****************************//
    private void insertTableActionPerformed() {
        InsertTableDialog x = new InsertTableDialog(this);
        x.setLocationRelativeTo(this);
        x.setVisible(true);

        if ((x.getRow() != 0) || (x.getCol() != 0)) { //Making sure 'cancel' wasn't clicked
            new InsertTableCommand(x.getRow(), x.getCol(), getActiveContext()).execute(commandDistributor, commandMediator);
        }
    }

    /**
     * Shows the dialog requesting input for the list, and then
     *
     * @param e
     */
    private void insertListActionPerformed(ActionEvent e) {
        SizeOfListDialog x = new SizeOfListDialog(this, true);
        x.setLocationRelativeTo(this);
        x.setVisible(true);
        int sizeOfList = x.getSizeOfList();

        if (sizeOfList != 0) { //Making sure 'cancel' wasn't clicked
            if (e.getSource() == insertNumberList) {
                new InsertListCommand(InsertListCommand.ListType.NUMBERED, sizeOfList, getActiveContext()).execute(commandDistributor, commandMediator);
            } else if (e.getSource() == insertBulletList) {
                new InsertListCommand(InsertListCommand.ListType.BULLETED, sizeOfList, getActiveContext()).execute(commandDistributor, commandMediator);
            } else if (e.getSource() == insertDictionaryList) {
                new InsertListCommand(InsertListCommand.ListType.DICTIONARY, sizeOfList, getActiveContext()).execute(commandDistributor, commandMediator);
            }
        }
    }

    /**
     * Insert various headers
     */
    private void insertH1ActionPerformed() {
        insertHActionPerformed(InsertConstructCommand.Construct.H1);
    }

    private void insertH2ActionPerformed() {
        insertHActionPerformed(InsertConstructCommand.Construct.H2);
    }

    private void insertH3ActionPerformed() {
        insertHActionPerformed(InsertConstructCommand.Construct.H3);
    }

    private void insertHActionPerformed(InsertConstructCommand.Construct h) {
        new InsertConstructCommand(h, getActiveContext()).execute(commandDistributor, commandMediator);
    }

    /**
     * prompts for image source and inserts image tag in HTML Editor
     */
    private void insertImageActionPerformed() {
        URLDialog dialog = new URLDialog(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        String src = dialog.getURL();
        if (src != "") {
            new InsertImageCommand(src, getActiveContext()).execute(commandDistributor, commandMediator);
        }

    }

    /**
     * prompts for tag href and name and inserts it in editor
     */
    private void insertATagActionPerformed() {
        URLWithNameDialog dialog = new URLWithNameDialog(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        String href = dialog.getURL();
        String name = dialog.getName();
        if (href != "" && name != "") {
            new InsertATagCommand(href, name, getActiveContext()).execute(commandDistributor, commandMediator);
        }
    }

    /**
     * inserts font emphasis "<b> tag for example </b>"
     *
     * @param e - Action event that has a source for either bold or italics
     */
    private void fontEmphasisActionPerformed(ActionEvent e) {
        if (e.getSource() == boldMenuItem) {
            new InsertConstructCommand(InsertConstructCommand.Construct.BOLD, getActiveContext()).execute(commandDistributor, commandMediator);
        } else if (e.getSource() == italicsMenuItem) {
            new InsertConstructCommand(InsertConstructCommand.Construct.ITALICS, getActiveContext()).execute(commandDistributor, commandMediator);
        }
    }


    //******************************** END *******************************************//

    //******************************* Action Performed for Option > X *****************//

    /**
     * Action performed when pressing the word wrap menu item
     */
    private void toggleWordWrapActionPerformed() {
        for (TabFrame tabFrame : tabFrames) {
            tabFrame.getTextPane().setLineWrap(toggleWordWrapMenuItem.getState());
        }
    }

    /**
     * Action performed method when pressing the tabWidth button.
     * Launches a dialog for the user to set the dat width.
     */
    private void tabWidthActionPerformed() {
        TabWidthDialog x = new TabWidthDialog(this, true, globalTabSize);
        x.setLocationRelativeTo(this);
        x.setVisible(true);
        int tabSize = x.getTabWidth();
        if (tabSize != 0) {
            globalTabSize = tabSize;
            for (TabFrame tabFrame : tabFrames) {
                tabFrame.getTextPane().setTabSize(tabSize);
            }
        }
    }

    private void toggleLinksViewActionPerformed() {
        for (TabFrame tabFrame : tabFrames) {
            tabFrame.setLinkViewVisible(toggleLinksViewMenuItem.getState());
        }
    }

    /**
     * Arranges the list in alphabetical order
     */
    private void alphabeticalRadioButtonActionPerformed() {
        this.SelectedListType = ListType.ALPHABETICAL;
        refreshLinkList();
    }

    /**
     * Arranges the list in alphabetical order
     */
    private void inOrderRadioButtonActionPerformed() {
        this.SelectedListType = ListType.INORDER;
        refreshLinkList();
    }


    //******************************** END *******************************************//

    //******************************* Action Performed for HTML > X *****************//

    /**
     * Calls Render Command that pops up a preview of the HTML on the current file
     */
    private void renderActionPerformed() {
        new RenderPreviewCommand(getActiveContext()).execute(commandDistributor, commandMediator);
    }

    /**
     * Refreshes the view of the List
     */
    private void refreshLinksMenuItemActionPerformed() {
        refreshLinkList();
    }

    /**
     * Helper method to refresh the link list at the bottom.
     * Called by multiple methods and after many commands to keep it updated.
     */
    private void refreshLinkList() {
        if (SelectedListType == ListType.ALPHABETICAL) {
            alphabeticalRadioButton.setEnabled(true);
            new RefreshLinksCommand(getActiveContext()).execute(commandDistributor, commandMediator);
        } else {
            inOrderRadioButton.setEnabled(true);
            new RefreshLinksCommand(getActiveContext()).execute(commandDistributor, commandMediator);
        }
    }

    /**
     * toggle the outline view
     */
    private void toggleOutlineActionPerformed() {
        new ValidateCommand(commandDistributor.getFileManager().getPathAt(activePaneIndex), false, getActiveContext()).execute(commandDistributor, commandMediator);
        getActiveTabFrame().toggleOutlineView();
        CloseTabComponent x = (CloseTabComponent) tabPane.getTabComponentAt(tabPane.getSelectedIndex());
        if (getActiveTabFrame().isInOutline()) {
            x.setLabelTitle(x.getName() + " [outline mode]");
        } else {
            x.setLabelTitle(x.getName());
        }
    }


    //******************************** END *******************************************//

    /**
     * This methods adds the listeners to the text area to do things like auto indenting
     * and also adds the backend document listener to its respective JTextArea
     *
     * @param newEditorPane - The new text area,
     */
    private void addListeners(JTextArea newEditorPane) {
        // Attach a keylistener to allow for auto-indentation
        newEditorPane.addKeyListener(new KeyListener() {
            // Need this variable for tabs, the selected text is only present on
            // keyPressed and turns to null on keyReleased.
            String selected = "";

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Get selected texts for the keyRelease here.
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    selected = getActivePane().getSelectedText();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER) {
                    try {
                        if (toggleAutoIndentMenuItem.getState()) {
                            new AutoIndentCommand(getActiveContext()).execute(commandDistributor, commandMediator);
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }

                } else if (keyCode == KeyEvent.VK_TAB) {
                    if (selected != null) {
                        new TabSelectedCommand(selected, getActiveContext()).execute(commandDistributor, commandMediator);
                    }
                }
            }
        });

        // Adding the document Listener
        newEditorPane.getDocument().addDocumentListener(commandDistributor.getFileManager().getFileAt(tabPane.getTabCount() - 1));
    }

    /**
     * @return -  get the pane of the currently selected tab
     */
    private JTextArea getActivePane() {
        if (!(tabFrames.size() <= 0)) {
            return tabFrames.get(activePaneIndex).getTextPane();
        }
        return null;

    }

    /**
     * @return - The currently showing tab frame object.
     */
    private TabFrame getActiveTabFrame() {
        if (!(tabFrames.size() <= 0)) {
            return tabFrames.get(activePaneIndex);
        }
        return null;

    }

    /**
     * @return The index of the active pane
     *         Will probably be removed
     */
    public int getActivePaneIndex() {
        return activePaneIndex;
    }

    /**
     * Static method in order for the AutoIndentCommand to work
     *
     * @param str, the string of the file contents to be indented
     * @return number of tabs that are in the current emitting place
     */
    public static int getTabCount(String str) {
        int tabCount = 0;
        for (char c : str.toCharArray()) {
            if (c == '\t')
                tabCount++;
            else
                break;
        }

        return tabCount;
    }

    /**
     * @param pane - the Active Pane
     * @return - The string of the current line edited
     */
    public static String getCurrentLine(JTextArea pane) {
        return getLine(pane, 0);
    }

    /**
     * @param pane - the Active Pane
     * @return - The string of the previous line being edited
     */
    public static String getPreviousLine(JTextArea pane) {
        return getLine(pane, -1);
    }

    private static String getLine(JTextArea pane, int offset) {
        int index;
        try {
            index = pane.getLineOfOffset(pane.getCaretPosition());
        } catch (BadLocationException e) {
            // Not sure if we can do much.  Assume that there is no cursor
            // and that we don't need to auto-indent.
            return null;
        }

        String[] content = pane.getText().split("\n");
        if (index + offset < 0 || index + offset >= content.length)
            return "";
        return content[index + offset];
    }

    /**
     * Static methods that will indent the tabs given the current count of the tab count.
     *
     * @param tabCount - the number of tabs prepending the edit
     * @return string of tabs to auto indent
     */
    public static String indentTabs(int tabCount) {
        String tabs = "";
        for (; tabCount > 0; tabCount--) {
            tabs += "\t";
        }
        return tabs;
    }

    /**
     * @return - what the global tab size is.
     */
    public int getGlobalTabSize() {
        return globalTabSize;
    }

    /**
     * @return - if word wrap is set or not.
     */
    public boolean getWordWrapBoolean() {
        return toggleWordWrapMenuItem.getState();
    }

    /**
     * Get tab frame
     *
     * @param index
     * @return
     */
    public TabFrame getTabFrame(int index) {
        if (tabFrames == null || tabFrames.size() == 0)
            return null;
        return tabFrames.get(index);
    }

    public ActiveContext getActiveContext() {
        return new ActiveContext(getActivePaneIndex(),
                getActivePane(), this, getTabFrame(activePaneIndex), SelectedListType);
    }


}

