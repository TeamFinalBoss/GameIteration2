package model.effect;

import model.entity.Entity;
import model.stats.PlayerStats;

/**
 * TODO: add to this class when stat changes are implemented
 * Is an example OneTimeEffect class. Will probably be used by basic attacks.
 * @author Jason Owens
 */
public abstract class DealDamageEffect implements Effect {
    int damageToDeal;

    public DealDamageEffect(int damageToDeal) {
        super();
        this.damageToDeal = damageToDeal;
    }
    
    public void setDamageToDeal(int damage){
    	this.damageToDeal = damage;
    }
    
    /**
     *
     * @param entityToAffect
     */
    @Override
    public void applyEffect(Entity entityToAffect){
        entityToAffect.dealDamage(damageToDeal);
    }
}
