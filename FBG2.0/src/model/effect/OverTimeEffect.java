package model.effect;

import model.entity.Entity;
import model.util.GameTimer;

/**
 * facilitates Effects that aren't temporary (apply stat change, then revert it later) 
 * but rather gradually apply their effects over time
 * @author Jason Owens
 */
public abstract class OverTimeEffect extends TimerEffect{
    int lifetime; //in milliseconds
    int refreshRate; //in milliseconds
    
    public OverTimeEffect(int lifetimeInMilliSeconds){
        super();
        lifetime = lifetimeInMilliSeconds;
        myTimer = GameTimer.getInstance();
        refreshRate = 100; //by default, OverTimeEffects run every 1/10 of a second
    }
    
    /**
     * Call this to create a new Effect of type OverTimeEffect and attach it to entityToAffect
     * @param entityToAffect Entity to affect
     * @author Jason Owens
     * @return new OverTimeEffect 
     */
    public abstract void applyEffect(Entity entityToAffect);
}
