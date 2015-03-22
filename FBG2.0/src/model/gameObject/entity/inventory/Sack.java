package model.gameObject.entity.inventory;

import model.gameObject.item.Takeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

/**
 * A Sack object is a component of an entity's inventory and holds its
 * non-equipped items.
 *
 * @author Matthew Kroeze
 * @version 1.0.0 2015-03-14
 */
public class Sack extends Observable {

    private int currentSelection = 0;
    private Boolean isVisible = false;
    private ArrayList<Takeable> contents;
    private int capacity;

    private boolean validatePosition(int position) {
        return position >= 0 && position < size();
    }

    /* -------------------- CONSTRUCTORS -------------------- */
    public Sack() {
        this(5);
    }

    /**
     * Creates a Sack of the specified size
     *
     * @param cap defines the capacity of the sack
     */
    public Sack(int cap) {
        contents = new ArrayList<Takeable>();
        capacity = cap;
    }

    /* -------------------- ACCESSORS -------------------- */
    /**
     * Returns the current number of items in the sack
     *
     * @return an <code>int</code> representation of the sack size
     */
    public int size() {
        return contents.size();
    }

    /**
     * Returns the maximum capacity of the sack
     *
     * @return an <code>int</code> representation of the sack capacity
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Returns the ordered contents of the sack
     *
     * @return an unmodifiable <code>List</code> of the sack contents
     */
    public List<Takeable> contents() {
        return Collections.unmodifiableList(contents);
    }

    /* -------------------- MUTATORS -------------------- */
    /**
     * Adds the given takeable item to the next free slot in the sack.
     *
     * @param newest the item to be added to the sack
     * @return <code>true</code> if the item was added successfully.
     * <code>false</code> otherwise.
     */
    public boolean insert(Takeable newest) {
        if (size() >= capacity()) {
            return false;
        }
        contents.add(newest);
        setChanged();
        notifyObservers(this);
        return true;
    }

    /**
     * Removes the takeable item in the specified slot from the sack.
     *
     * @param position the position of the item to be removed in the sack.
     * @return the removed <code>Takeable</code> object.
     * @throws <code>IllegalArgumentException</code> if <code>position</code> is
     * not in range [0,size()).
     */
    public Takeable remove(int position) {
        validatePosition(position);
        Takeable t = contents.remove(position);
        setChanged();
        notifyObservers(this);
        return t;
    }

    /**
     * Uses the takeable item in the specified slot of the sack.
     *
     * @param position the position of the item to be used in the sack.
     */
    public void use(int position) {
        validatePosition(position);
        contents.get(position).useInSack();
        setChanged();
        notifyObservers(this);
    }

    public Boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public int getCurrentSelection() {
        return currentSelection;
    }

    public void setCurrentSelection(int currentSelction) {
        this.currentSelection = currentSelction;
        setChanged();
        notifyObservers(this);
    }
    
    
    

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o); //Same as super but we want to update view as soon as viewport is added as an observer
        setChanged();
        notifyObservers(this);
    }
}
