package model.entity.inventory;
import model.entity.Entity;
import model.item.Equipable;
import model.item.EquipSlot;

import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
/** 
 * An Armory object is a component of an entity's inventory and holds its equipped items.
 * @author Matthew Kroeze
 * @version 1.0.0 2015-03-15
 */
public class Armory {
	private HashMap<EquipSlot,Equipable> contents;
	private Entity owner;
	
	/* -------------------- CONSTRUCTORS -------------------- */
	
	/** Creates an empty Armory */
	public Armory(Entity owner){
		this.owner = owner;
		contents = new HashMap<EquipSlot,Equipable>();
	}
	
	/* -------------------- ACCESSORS -------------------- */
	
	/** 
	 * Returns the contents of the armory.
	 * @return an immutable Map object with all Equipable objects
	 * 		   in the armory as values and their EquipSlots as keys.
	 */
	public Map<EquipSlot,Equipable> contents(){
		return Collections.unmodifiableMap(contents);
	}
	
	/* -------------------- MUTATORS -------------------- */ 
	/** 
	 * Equips the passed Equipable object if the owner of the armory meets that Equipable's equip requirements. 
	 * @param newest the Equipable object to be equipped.
	 * @return <code>null</code> if the new equipment's slot was empty.
	 * 		   the previously occupying Equipable of that slot if the new equipment's slot was filled.
	 * @throws IllegalArgumentException if the owner of the armory does not meet the new equipment's requirements
	 */
	public Equipable equip(Equipable newest){
		Equipable old = null;
		//fail if owner of armory does not meet the equipment's requirements
		if(!newest.meetsRequirements(owner)) throw new IllegalArgumentException("Equip failed: Owner does not meet equipment requirements");
		//unequip the slot if something is already occupying it
		if(contents.containsKey(newest.slot())){
			old = unequip(newest.slot());
		}
		//insert the new equipment into the armory
		contents.put(newest.slot(), newest);
		//inform the equipable that it has been equipped so that it may perform its on-equip effects
		newest.onEquip(owner);
		//return whatever equipment was removed
		return old;
	}
	
	/** 
	 * Unequips and returns the equipment in the specified slot if that slot is occupied.
	 * @param slot the EquipSlot of the Equipable item to be unequipped.
	 * @return the Equipable item that was unequipped.
	 * @throws IllegalArgumentException if no equipment occupied the specified slot.
	 */
	public Equipable unequip(EquipSlot slot){
		//verify that the slot is occupied
		if(!contents.containsKey(slot)){
			throw new IllegalArgumentException("There is no item to be unequipped in the " + slot + " slot");
		}
		//inform the equipment that it is being unequipped and return it
		Equipable old = contents.remove(slot);
		old.onUnequip(owner);
		return old;
	}

}
