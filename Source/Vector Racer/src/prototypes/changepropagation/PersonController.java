package prototypes.changepropagation;

import javafx.event.Event;
import javafx.event.EventHandler;

public class PersonController implements EventHandler {

    private PersonView personView;
    private PersonModel personModel;

    public PersonController(PersonView personView, PersonModel personModel){
        this.personView = personView;
        this.personModel = personModel;
    }

    @Override
    public void handle(Event event) {

    }
}
