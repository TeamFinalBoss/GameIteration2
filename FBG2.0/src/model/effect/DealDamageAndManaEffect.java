package model.effect;

import model.entity.Entity;

/**
 * Class to deal both mana damage and HP damage in one effect
 * @author Michael Cohen
 *
 */
public class DealDamageAndManaEffect extends DealDamageEffect {
	
	private int manaToDeal;
	
	public DealDamageAndManaEffect(int damageToDeal, int manaToDeal){
		super(damageToDeal);
		this.manaToDeal = manaToDeal;
	}
	
	public void setManaToDeal(int damage){
		this.manaToDeal = damage;
	}
	
	protected int getManaToDeal(){ return this.manaToDeal;}
	
	@Override
	public void applyEffect(Entity entityToAffect, int distance){
		if (distance <= 0) throw new IllegalArgumentException("Cannot have a 0 distance");
		entityToAffect.dealDamage((int) (this.getDamageToDeal() / Math.sqrt(distance)));
		entityToAffect.modifyCurrentMP((int) (this.getManaToDeal() / Math.sqrt(distance)));
	}

}
