package model.ability.effects;

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
    
    public OverTimeEffect(Entity entityToEffect, int lifetimeInMilliSeconds){
        super(entityToEffect);
        lifetime = lifetimeInMilliSeconds;
        myTimer = GameTimer.getInstance();
        myTimer.addEvent(this, 0); //immediately call run, which will then add itself again
        refreshRate = 100; //by default, OverTimeEffects run every 1/10 of a second
    }
}
