package model.entity;
import java.util.ArrayList;
import java.util.List;

import model.item.Takeable;

/** A storefront to control buying and selling of items to a vendor.
 * @author Aidan Pace & Code from Matthew Kroeze
 */
public class Storefront {
	private List<StoreEntry> contents;
	
	private void validatePosition(int position){
		if(position < 0 || position >= contents.size()){
			throw new IllegalArgumentException("Invalid position accessed in storefront.");
		}
	}
	
	public Storefront() {
		contents = new ArrayList<StoreEntry>();
	}
	
	/** Returns the ordered contents of the store
	 * @return an unmodifiable <code>List</code> of the store contents 
	 */
	public List<Takeable> contents() {
		List<Takeable> t = new ArrayList<Takeable>();
		
		for(StoreEntry e : contents) {
			t.add(e.getItem());
		}
		
		return t;
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
		return (contents.get(position).getItem().getValue() * -1);
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
		StoreEntry e = contents.get(position);
		Takeable i = e.vendItem();
		if(e.getAmt() == 0) contents.remove(position);
		return i;
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
		
		for(StoreEntry e : contents) {
			if(e.getItem().getName().equals(item.getName())) {
				e.addItem(item);
				return item.getValue();
			}
		}
		
		StoreEntry n = new StoreEntry(item, 1);
		contents.add(n);
		return item.getValue();
	}
	
	private class StoreEntry {
		Takeable item;
		int amt;
		
		public StoreEntry(Takeable item, int amt) {
			this.item = item;
			this.amt = amt;
		}
		
		public Takeable getItem() { return item; }
		public int getAmt() { return amt; }
		
		public void addItem(Takeable item) {
			if(amt == -1) return;
			amt += 1;
			return;
		}
		
		public Takeable vendItem() {
			if(amt >= 0) {
				amt -= 1;
				return item.copy();
			}
			
			if(amt == -1) {
				return item.copy();
			}
			
			return null;
		}
	}

}
