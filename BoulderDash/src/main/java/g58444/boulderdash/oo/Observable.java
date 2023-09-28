package g58444.boulderdash.oo;

import java.util.List;

public interface Observable {
    /**
     * This method registers a new observer to an observable class. Each observable should have a list of all his
     * observers. This method simply adds the given observer in arguments to that list.
     *
     * @param observer The observer to add to the observable.
     */
    public void registerObserver(Observer observer);

    /**
     * This method is the opposite of register, it removes an already added observer to an observable from his list.
     *
     * @param observer The observer to remove.
     */
    public void removeObserver(Observer observer);

    /**
     * This method is used to notify all his observers about a certain change of the observable that has to be
     * passed on the observers.
     */
    public void notifyObserver();
}
