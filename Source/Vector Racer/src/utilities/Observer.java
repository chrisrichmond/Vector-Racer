package utilities;

/**
 * Interface representing an observer of an Observable object instance
 */
public interface Observer {

    /**
     * Called by an Observable object upon this Observer object in order for it to carry out necessary update operations
     */
    void update();
}

