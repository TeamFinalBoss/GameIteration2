package model.item;

import model.effect.DealDamageEffect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;


/**
 * ID: 32
 * @author Michael Cohen
 *
 */
public class MattSpear extends Weapon {

	public MattSpear(){		
		super("MattSpear", "Generic spear description", new CoordinatePair(), 
				5, 1, EquipSlot.TWO_HAND, new DealDamageEffect(95));
		this.setID("32");
		this.setClassName("MattSpear");
	}
	
	public MattSpear(String objectName, String description, CoordinatePair location, int durability){
		super(objectName, description, location, 5, durability, EquipSlot.TWO_HAND, new DealDamageEffect(95));
		
		this.setID("32");
		this.setClassName("MattSpear");
	}
	
	@Override
	public void onUnequip(Entity target) {
		target.modifyWeaponOffense(-25);
		target.modifyMovement(5);

	}

	@Override
	public void onEquip(Entity target) {
		target.modifyWeaponOffense(25);
		target.modifyMovement(-5);

	}

	@Override
	public Takeable copy() {
		return new MattSpear(this.getName(), this.getDescription(), this.getLocation(), this.getDurability());
	}

}
