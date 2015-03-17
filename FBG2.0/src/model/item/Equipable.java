package model.item;
import model.entity.Entity;

public class Equipable extends Takeable {
	public EquipSlot slot() {
		//TODO: Make this function return the actual slot of the Equipable instance
		return EquipSlot.HEAD;
	}
	public void onEquip(Entity target){
		//TODO: perform all effects on entity that is equipping this item
	}
	public void onUnequip(Entity target){
		//TODO: undo all effects on entity that is unequipping this item
	}
	public boolean meetsRequirements(Entity target){
		//TODO: return true iff target entity meets equip requirements
		return true;
	}
}
