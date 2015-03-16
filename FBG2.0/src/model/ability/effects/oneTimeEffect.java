package model.ability.effects;

import model.entity.Entity;

/**
 * Immediately affects the entity on construction. 
 * @author Jason Owens
 */
public abstract class oneTimeEffect extends Effect {
    
    public oneTimeEffect(Entity entityToAffect){
        super(entityToAffect);        
    }
    
    protected abstract void applyEffect();
}
