package changepropagation;

public interface Observer {

//    /**
//     * Called by an Observable object upon this Observer object in order for it to carry out necessary update operations
//     * @param o the Observable object calling this method externally
//     */
//    void update(Observable o);

    /**
     * Called by an Observable object upon this Observer object in order for it to carry out necessary update operations
     */
    void update();
}
