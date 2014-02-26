package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;
import TeamTwoHTMLEditor.OutlineEditorKit.OutlineEditorKit;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Keegan
 * Date: 4/16/13
 * Time: 2:56 PM
 * Accesses the third party app that allows us to view the HTML in an outlined
 * form.  This outline has limited capabilities and does not allow edits while
 * it is being displayed.
 */
public class ViewOutlineCommand implements Command{

	private final ActiveContext context;

	public ViewOutlineCommand(ActiveContext context){
		this.context = context;

	}

	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		JDialog outlineFrame = new JDialog(context.getParent(), true);
		outlineFrame.setSize(context.getParent().getSize());
		outlineFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		outlineFrame.setTitle("Outline mode for HTML File");

		JEditorPane outlinePreview = new JEditorPane();
		outlinePreview.setEditorKit(new OutlineEditorKit());
        outlinePreview.setText(context.getActiveTextArea().getText());
        outlinePreview.setEditable(false);

		outlineFrame.add(new JScrollPane(outlinePreview));
		outlineFrame.setLocationRelativeTo(context.getParent());

		outlineFrame.setVisible(true);

	}
}
