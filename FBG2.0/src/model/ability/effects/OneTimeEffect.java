package model.ability.effects;

import model.entity.Entity;

/**
 * Immediately affects the entity on construction. 
 * Separate from TimerEffect.
 * Calls applyEffect on construction.
 * @author Jason Owens
 */
public abstract class OneTimeEffect {
    Entity myEntity;
    
    public OneTimeEffect(Entity entityToAffect){
        myEntity = entityToAffect;    
        applyEffect();
    }
    
    protected abstract void applyEffect();
}
