package model.gameObject.entity.inventory;
import model.item.Equipable;
import model.item.EquipSlot;

import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
/** An Armory object is a component of an entity's inventory and holds its equipped items.
 * @author Matthew Kroeze
 * @version 1.0.0 2015-03-15
 */
public class Armory {
	private HashMap<EquipSlot,Equipable> contents;
	
	/* -------------------- CONSTRUCTORS -------------------- */
	
	/** Creates an empty <code>Armory</code> */
	public Armory(){
		contents = new HashMap<EquipSlot,Equipable>();
	}
	
	/* -------------------- ACCESSORS -------------------- */
	
	/** Returns the contents of the armory
	 * @return a <code>Map</code> object with all <code>Equipable</code> objects
	 * 		   in the armory as values and their <code>EquipSlot</code>s as keys.
	 */
	public Map<EquipSlot,Equipable> contents(){
		return Collections.unmodifiableMap(contents);
	}
	
	/* -------------------- MUTATORS -------------------- */ 
	/** Equips the passed <code>Equipable</code> object. Is NOT responsible for ensuring
	 *  that the effects and stat changes of the equipment are applied. Is NOT responsible 
	 *  for verifying that the equipee meets the requirements of the equipment.
	 * @param newest the <code>Equipable</code> object to be equipped
	 */
	public void equip(Equipable newest){
		if(contents.containsKey(newest.slot())){
			unequip(newest.slot());
		}
		contents.put(newest.slot(), newest);
	}
	
	/** Unequips and returns the equipment in the specified slot. Is NOT responsible for
	 *  undoing the stat changes of that equipment
	 * @param slot the <code>EquipSlot</code> of the <code>Equipable</code> item to be unequipped
	 * @return the <code>Equipable</code> item that was unequipped
	 * @throws <code>IllegalArgumentException</code> if no equipment occupied the specified slot
	 */
	public Equipable unequip(EquipSlot slot){
		if(!contents.containsKey(slot)){
			throw new IllegalArgumentException("There is no item to be unequipped in the " + slot + " slot");
		}
		return contents.remove(slot);
	}

}
