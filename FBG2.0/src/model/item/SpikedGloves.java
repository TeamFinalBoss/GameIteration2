package model.item;

import model.effect.DealDamageEffect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 * ID: 33
 * @author Michael Cohen
 *
 */
public class SpikedGloves extends Weapon {
	
	public SpikedGloves(){
		super("SpikedGlove", "Generic description", new CoordinatePair(), 
				0, 1, EquipSlot.HANDS, new DealDamageEffect(15));
		setID("33");
	}
	
	public SpikedGloves(String objectName, String description, CoordinatePair location,
			int durability){
		super(objectName, description, location, 5, durability,
				EquipSlot.HANDS, new DealDamageEffect(15));
		
		setID("33");
	}

	@Override
	public void onUnequip(Entity target) {
		target.modifyWeaponOffense(-15);
	}

	@Override
	public void onEquip(Entity target) {
		target.modifyWeaponOffense(15);

	}

	@Override
	public Takeable copy() {
		return new SpikedGloves(getName(), getDescription(), getLocation(), getDurability());
	}

}
