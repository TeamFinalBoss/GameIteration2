package model.entity;
import model.item.Takeable;

/** A storefront to control buying and selling of items to a vendor.
 * @author Aidan Pace & Code from Matthew Kroeze
 */
public class Storefront {
	private List<Takeable> contents;
	
	private void validatePosition(int position){
		if(position < 0 || position >= contents.size()){
			throw new IllegalArgumentException("Invalid position accessed in storefront.");
		}
	}
	
	public Storefront() {
		contents = new ArrayList<Takeable>();
	}
	
	/** Returns the ordered contents of the store
	 * @return an unmodifiable <code>List</code> of the store contents 
	 */
	public List<Takeable> contents() {
		return Collections.unmodifiableList(contents);
	}
	
	/**
	 * This method checks the item that exists in the requested position
	 * inside the storefront, then returns the change in currency that will occur if this item
	 * is bought.
	 * 
	 * @param position the requested position in the store
	 * @return the change in currency that will occur
	 */
	public int payForItem(int position) {
		validatePosition(position);
		return (contents.get(position).getValue() * -1);
	}
	
	/**
	 * This method removed an item from the requested position in the store,
	 * then returns the removed item.
	 * 
	 * @param position the requested position in the store
	 * @return the removed Takeable item.
	 */
	public Takeable buyItem(int position) {
		validatePosition(position);
		return contents.remove(position);
	}
	
	/**
	 * This method adds the requested item to the store, then
	 * returns the amount of currency that the item's owner will
	 * receive for the item.
	 * 
	 * @param item The Takeable item to be added to the store
	 * @return the change in currency that will occur
	 */
	public int sellItem(Takeable item) {
		contents.add(item);
		return item.getValue();
	}

}
