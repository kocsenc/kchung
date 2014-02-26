package TeamTwoHTMLEditor.command;

import TeamTwoHTMLEditor.CommandDistributor;
import TeamTwoHTMLEditor.CommandMediator;

/**
 * Handles delegating the task of saving.
 */
public class SaveCommand implements Command {

    private final ActiveContext context;

    public SaveCommand(ActiveContext context) {
        this.context = context;
    }

    @Override
    public void execute(CommandDistributor c, CommandMediator cmd) {
        String contents = context.getActiveTextArea().getText();
        String path = c.getFileManager().getPathAt(context.getIndex());
        c.getFileManager().quickSaveFile(path, contents, context.getIndex());
        cmd.saveCommandExecuted(path, false, c, context);
        c.getFileManager().printStatus();
    }
}
