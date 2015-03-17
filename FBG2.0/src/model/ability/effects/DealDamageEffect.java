package model.ability.effects;

import model.entity.Entity;
import model.stats.PlayerStats;

/**
 * TODO: add to this class when stat changes are implemented
 * Is an example OneTimeEffect class. Will probably be used by basic attacks.
 * @author Jason Owens
 */
public class DealDamageEffect extends OneTimeEffect {
    private int damageToDeal;

    public DealDamageEffect(Entity entityToAffect, int damageToDeal) {
        super(entityToAffect);
        this.damageToDeal = damageToDeal;
        applyEffect();
    }
    
    @Override
    protected final void applyEffect(){
        myEntity.dealDamage(damageToDeal);
    }
}
