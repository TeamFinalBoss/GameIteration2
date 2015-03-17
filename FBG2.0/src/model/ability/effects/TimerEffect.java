
package model.ability.effects;

import java.util.TimerTask;
import model.entity.Entity;
import model.util.GameTimer;

/**
 * Changes an Entity based on it's desired effect
 * @author Jason Owens
 */
public abstract class TimerEffect extends TimerTask implements Effect{
    protected Entity myEntity;
    protected GameTimer myTimer;
    
    public TimerEffect(){
        myTimer = GameTimer.getInstance();
    }
    
    /**
     * applies effect to a given Entity
     * @param entityToAffect
     */
    public abstract void applyEffect(Entity entityToAffect);
}
