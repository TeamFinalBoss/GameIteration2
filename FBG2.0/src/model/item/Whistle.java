package model.item;

import model.entity.Entity;
import model.map.pair.CoordinatePair;

public abstract class Whistle extends Equipable {
	
	public Whistle(){
		super("Whistle", "Generic description", new CoordinatePair(),
				0, 1, EquipSlot.MAIN_HAND);
		
		this.id = "25";
		this.className = "Whistle";
	}
	
	public Whistle(String objectName, String description, CoordinatePair location, 
			int value, int durability, EquipSlot slot){
		super(objectName, description, location, value, durability, slot);
		
		this.id = "25";
		this.className = "Whistle";
	}

	@Override
	public void onUnequip(Entity target) {
		target.modifyObservation(-50);
		target.modifyStrength(50);
	}

	@Override
	public void onEquip(Entity target) {
		target.modifyObservation(50);
		target.modifyStrength(-50); //hehe

	}

	@Override
	public Takeable copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
