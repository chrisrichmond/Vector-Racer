package prototypes.changepropagation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonModel {

    StringProperty firstName = new SimpleStringProperty(this, "firstName", " ");

    // Returns the StringProperty object
    public StringProperty firstNameProperty() {
        return firstName;
    }

    // Returns the firstName value
    public String getFirstName() {
        return firstName.get();
    }

    // Sets the firstName value
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
}
