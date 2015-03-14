package model.entities;
import model.item.Takeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/** A Sack object is a component of an entity's inventory and holds its non-equipped items.
 * @author Matthew Kroeze
 * @version 1.0.0 2015-03-14
 */

public class Sack {
	private ArrayList<Takeable> contents;
	private int capacity;
	
	private void validatePosition(int position){
		if(position < 0 || position >= size()){
			throw new IllegalArgumentException("An item removal attempt in a sack occured with an invalid item position.");
		}
	}
	
	/* -------------------- CONSTRUCTORS -------------------- */
	
	/** Creates a Sack of the specified size  
	 * @param cap defines the capacity of the sack
	 */
	public Sack(int cap){
		contents = new ArrayList<Takeable>();
		capacity = cap;
	}
	
	/* -------------------- ACCESSORS -------------------- */
	
	/** Returns the current number of items in the sack
	 * @return an <code>int</code> representation of the sack size
	 */
	public int size(){
		return contents.size();
	}
	
	/** Returns the maximum capacity of the sack
	 *  @return an <code>int</code> representation of the sack capacity
	 */
	public int capacity(){
		return capacity;
	}
	
	/** Returns the ordered contents of the sack
	 * @return an unmodifiable <code>List<Takeable/></code> of the sack contents 
	 */
	public List<Takeable> contents(){
		return Collections.unmodifiableList(contents);
	}
	
	/* -------------------- MUTATORS -------------------- */
	
	/** Adds the given takeable item to the next free slot in the sack.
	 * @param newest the item to be added to the sack
	 * @return <code>true</code> if the item was added successfully.
	 * 		   <code>false</code> otherwise.
	 */
	public boolean insert(Takeable newest){
		if(size() >= capacity()){
			return false;
		}
		contents.add(newest);
		return true;
	}
	
	/** Removes the takeable item in the specified slot from the sack.
	 * @param position the position of the item to be removed in the sack.
	 * @return the removed <code>Takeable</code> object.
	 * @throws <code>IllegalArgumentException</code> if <code>position</code> is not in range [0,size()).
	 */
	public Takeable remove(int position){
		validatePosition(position);
		return contents.remove(position);
	}
	
	/** Uses the takeable item in the specified slot of the sack.
	 * 
	 * @param position the position of the item to be used in the sack.
	 * @throws <code>IllegalArgumentException</code> if <code>position</code> is not in range [0,size()).
	 */
	public void use(int position){
		validatePosition(position);
		contents.get(position).useInSack();
	}
	
}
