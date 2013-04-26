package TeamTwoHTMLEditor;

import java.util.LinkedList;
import java.util.List;

/**
 * Maintains a stack of previous states. The maximum size the CareTaker will
 * allow is two states. As more states are added to the CareTaker, previous
 * states are removed to maintain a stack size no greater than two.
 */
public class CareTaker {

    private List<Memento> undoStack;
    private int pointer;

    public CareTaker() {
        undoStack = new LinkedList<Memento>();
        pointer = 0;
    }

    public void storePrevious(Memento previous) {


        // If our pointer is currently in the middle of the undo stack, then
        // we need to invalidate all future redos to allow this undo state
        // to maintain a proper undo/redo mechanism.
        if (pointer != undoStack.size()) {
            undoStack = undoStack.subList(0, pointer + 1);
        }
		else if(undoStack.size() > 20){
			undoStack.remove(0);
		}
        undoStack.add(previous);
        pointer = undoStack.size() - 1;
    }


    /**
     * The Undo Operation.
     *
     * @return
     */
    public Memento retrievePrevious() {
        Memento previous = null;
        if (undoStack.size() != 0 && pointer != 0)
            previous = undoStack.get(--pointer);
        return previous;
    }

    /**
     * The Redo Opeartion
     *
     * @return
     */
    public Memento retrieveNext() {
        Memento next = null;
        if (undoStack.size() != 0 && pointer != undoStack.size() - 1)
            next = undoStack.get(++pointer);
        return next;
    }

    public boolean topOfStack() {
        return pointer == undoStack.size() - 1;
    }

    public void storeCurrent(Memento memento) {
        if (!undoStack.get(pointer).equals(memento)) {
            undoStack.add(memento);
            pointer = undoStack.size() - 1;
        }
    }
}
