package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList keptPersons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        keptPersons = new UniquePersonList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the kept person list with {@code keptPersons}.
     * {@code keptPersons} must not contain duplicate keptPersons.
     */
    public void setKeptPersons(List<Person> keptPersons) {
        this.keptPersons.setPersons(keptPersons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setKeptPersons(newData.getKeptPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the list of kept persons.
     */
    public boolean hasKeptPerson(Person person) {
        requireNonNull(person);
        return keptPersons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the list of kept persons.
     */
    public void addKeptPerson(Person p) {
        keptPersons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list of kept persons with {@code editedPerson}.
     * {@code target} must exist in the list of kept persons.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setKeptPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        keptPersons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from the list of kept persons in this {@code AddressBook}.
     * {@code key} must exist in the list of kept persons.
     */
    public void removeKeptPerson(Person key) {
        keptPersons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("keptPersons", keptPersons)
                .toString();
    }

    @Override
    public ObservableList<Person> getKeptPersonList() {
        return keptPersons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return keptPersons.equals(otherAddressBook.keptPersons);
    }

    @Override
    public int hashCode() {
        return keptPersons.hashCode();
    }
}
