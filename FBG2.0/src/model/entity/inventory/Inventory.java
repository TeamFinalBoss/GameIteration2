package model.entity.inventory;
import java.util.List;

import model.entity.Entity;
import model.item.Takeable;
import model.item.Equipable;

import java.util.Map;

import model.item.EquipSlot;
/** An Inventory object belongs to a singular Entity and holds the items of that entity.
 * @author Matthew Kroeze
 * @version 1.0.0 2015-03-14
 */
public class Inventory {
	private Sack mySack;
	private Armory myArmory;
	
	/* -------------------- CONSTRUCTORS -------------------- */
	
	/** Creates an <code>Inventory</code> instance with a Sack component and an Armory component.
	 * @param sackCap the capacity of the <code>Sack</code> component of the <code>Inventory</code> 
	 */
	public Inventory(int sackCap, Entity owner){
		mySack = new Sack(sackCap, owner);
		myArmory = new Armory(owner);
	}
	
	/* -------------------- ACCESSORS -------------------- */
	/** Returns the ordered contents of the sack
	 * 
	 * @return an immutable List of Takeables ordered as they were in the sack
	 */
	public List<Takeable> sackContents(){
		return mySack.contents();
	}
	
	/**
	 * Returns the size of the sack
	 * @return sack size as an <code>int</code>
	 */
	public int sackSize(){
		return mySack.size();
	}
	
	/**
	 * Returns the capacity of the sack
	 * @return sack capacity as an <code>int</code>
	 */
	public int sackCapacity(){
		return mySack.capacity();
	}
	
	/**
	 * Returns contents of the armory.
	 * @return an immutable Map with currently equipped item values
	 * 		   and currently equipped items' slots keys.
	 */
	public Map<EquipSlot,Equipable> armoryContents(){
		return myArmory.contents();
	}
	
	/* -------------------- MUTATORS -------------------- */
	/**
	 * Uses the item at the specified position within the sack 
	 * @param position indicates which item in the sack's ordered contents
	 * 				   is to be used, where the first item is at position 0.
	 */
	public boolean use(int position){
		return mySack.use(position);
	}
	
	/**
	 * Removes the item at the specified position within the sack
	 * @param position indicates which item in the sack's ordered contents
	 * 		  is to be removed, where the first item is 
	 * @return the removed Takeable item
	 */
	public Takeable remove(int position){
		return mySack.remove(position);
	}
	
	/**
	 * Removes the specified item from the sack
	 * @param item the Takeable item to be removed
	 * @return the removed Takeable
	 */
	public Takeable remove(Takeable item){
		return mySack.remove(item);
	}
	
	/**
	 * Inserts the specified item in the sack at the end of the order
	 * @param newest the Takeable to be inserted
	 * @return <code>true</code> iff the insertion was successful
	 */
	public boolean insert(Takeable newest){
		return mySack.insert(newest);
	}
	
	/**
	 * Equips the specified item in the armory
	 * @param newest the Equipable to be equipped
	 */
	public void equip(Equipable newest){
		myArmory.equip(newest);
	}
	
	/**
	 * Removes the equipment occupying the specified slot in the armory
	 * @param slot the slot which is to be made available
	 * @return the equipment which was removed from the slot
	 */
	public Equipable unequip(EquipSlot slot){
            Equipable e = myArmory.unequip(slot);
            if(e!=null){
                insert(e);
                return e;
            }
            return null;
	}

    public boolean useWeapon(Entity this1) {
      return  myArmory.useWeapon(this1);
        
    }

	public Sack getSack() {
		return this.mySack;
	}
    
}
