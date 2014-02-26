package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;

/**
 * Created with IntelliJ IDEA. User: Shannon Date: 4/4/13 Time: 2:43 PM
 * This command parses the HTML file and renders it.
 */
public class RenderPreviewCommand implements Command{

	private final ActiveContext context;

	public RenderPreviewCommand(ActiveContext context){
		this.context = context;

	}

	@Override
	public void execute(CommandDistributor c, CommandMediator cmd){
		JDialog previewFrame = new JDialog(context.getParent(), true);
		previewFrame.setSize(context.getParent().getSize());
		previewFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		previewFrame.setTitle("Preview of HTML File");

		JEditorPane preview = new JEditorPane();
		preview.setEditorKit(new HTMLEditorKit());
		preview.setText(context.getActiveTextArea().getText());
		preview.setContentType("text/html");
		preview.setEditable(false);

		previewFrame.add(new JScrollPane(preview));
		previewFrame.setLocationRelativeTo(context.getParent());

		previewFrame.setVisible(true);

	}
}
