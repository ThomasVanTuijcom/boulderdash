package g58444.boulderdash.model.commands;

/**
 * This interface represent the command interface. The methods written inside this interface needs to be implemented
 * by all command implementing this interface.
 */
public interface Command {
    /**
     * This method is called when a command has to be executed.
     */
    public void execute();

    /**
     * This method is called when we want to cancel a command we did.
     */
    public void unexecute();

    /**
     * This method is to know if the command is reversible or not. That means if the command could be undone or not.
     *
     * @return Returns true if it is reversible and false otherwise.
     */
    public boolean isReversible();
}
