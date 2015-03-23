package model.item;

import model.effect.DealDamageEffect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 * ID: 30
 * @author Michael Cohen
 *
 */
public class Warhammer extends Weapon {
	
	public Warhammer(){
		super("Warhammer", "Generic warhammer description", new CoordinatePair(), 
				5, 1, EquipSlot.TWO_HAND, new DealDamageEffect(100));
		this.setID("30");
		this.setClassName("Warhammer");
	}
	
	public Warhammer(String objectName, String description, CoordinatePair location,
			int durability){
		super(objectName, description, location, 5, durability, EquipSlot.TWO_HAND, new DealDamageEffect(100));
		
		this.setID("30");
		this.setClassName("Warhammer");
	}

	@Override
	public void onUnequip(Entity target) {
		target.modifyWeaponOffense(-30);
		target.modifyMovement(10);

	}

	@Override
	public void onEquip(Entity target) {
		target.modifyWeaponOffense(30);
		target.modifyMovement(-10);
	}

	@Override
	public Takeable copy() {
		return new Warhammer(this.getName(), this.getDescription(), this.getLocation(), this.getDurability());
	}

}
