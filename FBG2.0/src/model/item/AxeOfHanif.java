package model.item;

import model.effect.DealDamageAndManaEffect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 * ID: 31
 * @author Michael Cohen
 *
 */
public class AxeOfHanif extends Weapon {
	
	public AxeOfHanif(){
		super("AxeOfHanif", "Generic Axe of Hanif", new CoordinatePair(),
				100, 50, EquipSlot.TWO_HAND, new DealDamageAndManaEffect(75, 20));
		this.setID("31");
		this.setClassName("AxeOfHanif");
	}
	
	public AxeOfHanif(String objectName, String description, CoordinatePair location,
			int durability){
		super(objectName, description, location, 100, durability, EquipSlot.TWO_HAND, new DealDamageAndManaEffect(75, 20));
		
		this.setID("31");
		this.setClassName("AxeOfHanif");
	}

	@Override
	public void onUnequip(Entity target) {
		target.modifyWeaponOffense(-35);
		target.modifyMovement(15);

	}

	@Override
	public void onEquip(Entity target) {
		target.modifyWeaponOffense(35);
		target.modifyMovement(-15);

	}

	@Override
	public Takeable copy() {
		return new AxeOfHanif(
				this.getName(), this.getDescription(), this.getLocation(),
				this.getDurability());
	}

}
