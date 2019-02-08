package utilities;

public interface Observable {

    /**
     * Called by Observer objects to subscribe to this Observable object
     * @param o the Observer object which is subscribing
     */
    void attach(Observer o);

    /**
     * Called by Observer objects to unsubscribe to this Observable object
     * @param o the Observer object which is unsubscribing
     */
    void detach(Observer o);

    /**
     * Sets an internal 'changed' flag to true
     */
    void setChanged();

    /**
     * If internal 'changed' flag is true then will notify all Observers which are subscribed to this Observable object
     * by updating them individually, then resets 'changed' flag to false
     */
    void notifyObservers();


}
