package model.ability.effects;

import model.entity.Entity;

/**
 * 
 * @author Jason Owens
 */
public abstract class oneTimeEffect extends Effect {
    
    public oneTimeEffect(Entity entityToAffect){
        super(entityToAffect);        
    }
    
    protected abstract void applyEffect();
}
