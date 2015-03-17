package model.ability.effects;

import model.entity.Entity;


/**
 * TODO: add to this class when stat changes are implemented
 * For Effects that apply changes and then revert them after a certain amount of time
 * @author Jason owens
 */
public abstract class TemporaryEffect extends TimerEffect {
    int lifetime; //how long from now to revert, in milliseconds
    public TemporaryEffect(Entity entityToAffect){
        super(entityToAffect);
        applyEffect();
        myTimer.addEvent(this, lifetime);
    }
    
    @Override
    public void run(){
        applyInverseEffect();
    }
    
    /**
     * applies the effect on the entity, to remain until it is inverted by applyInverseEffect
     * @author Jason Owens
     */
    protected abstract void applyEffect();
    
    /**
     * reverts the entity back to his original state
     * @author Jason Owens
     */
    protected abstract void applyInverseEffect();
}
