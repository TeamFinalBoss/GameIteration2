package model.effect;

import model.entity.Entity;
import model.stats.PlayerStats;

/**
 * TODO: add to this class when stat changes are implemented
 * Is an example OneTimeEffect class. Will probably be used by basic attacks.
 * @author Jason Owens
 */
public abstract class DealDamageEffect implements Effect {
    private int damageToDeal;

    public DealDamageEffect(int damageToDeal) {
        super();
        this.damageToDeal = damageToDeal;
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
