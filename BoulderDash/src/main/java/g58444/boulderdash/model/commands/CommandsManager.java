package g58444.boulderdash.model.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the command manager, the invoker of our command design pattern. This class will manage the different
 * commands that will be done. It can register, undo or redo a command. It holds a list that represents the history
 * of all the commands that has been done.
 */
public class CommandsManager {
    List<Command> history = new ArrayList<>();
    int index = -1;

    /**
     * This method registers a given command as a command that has been executed, that means that it is registered
     * inside the history.
     *
     * @param command The command to register.
     */
    public void register(Command command) {
        history.add(command);
        index = history.size() - 1;
    }

    /**
     * This method is called to undo a move, it will search for the last added reversible command and unexecutes it. If
     * there is nothing to be undone it throws an exception.
     */
    public void undo() {
        if (index != -1) {
            Command command = history.get(index);
            if (command.isReversible()) {
                command.unexecute();
                index--;
            }
        }
    }

    /**
     * This method is called to redo a move, it will search for the unexecuted commands and redo them until there is no
     * command to be redone.
     */
    public void redo() {
        if (index != history.size() - 1) {
            index++;
            Command redoCommand = history.get(index);
            redoCommand.execute();
        }
    }
}
