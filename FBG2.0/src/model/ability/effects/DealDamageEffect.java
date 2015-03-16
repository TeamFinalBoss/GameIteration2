package model.ability.effects;

import model.entity.Entity;
import model.stats.PlayerStats;

/**
 * 
 * @author Jason Owens
 */
public class DealDamageEffect extends oneTimeEffect {
    private int damageToDeal;

    public DealDamageEffect(Entity entityToAffect, int damageToDeal) {
        super(entityToAffect);
        applyEffect();
    }
    
    @Override
    protected final void applyEffect(){
        myEntity.dealDamage(damageToDeal);
    }
}
