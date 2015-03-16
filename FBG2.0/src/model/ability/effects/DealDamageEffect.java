package model.ability.effects;

import model.entity.Entity;
import model.stats.PlayerStats;

/**
 *
 * @author Owner
 */
public class DealDamageEffect extends oneTimeEffect {
    private int damageToDeal;
    private PlayerStats myEffect;

    public DealDamageEffect(Entity entityToAffect, int damageToDeal) {
        super(entityToAffect);
        myEffect = new PlayerStats();
        myEffect.sethpCurrent(damageToDeal);
        applyEffect();
    }
    
    @Override
    protected final void applyEffect(){
        myEntity.statMerge();
    }
}
