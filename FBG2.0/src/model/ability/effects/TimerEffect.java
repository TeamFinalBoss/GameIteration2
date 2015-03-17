
package model.ability.effects;

import java.util.TimerTask;
import model.entity.Entity;
import model.util.GameTimer;

/**
 * Changes an Entity based on it's desired effect
 * @author Jason Owens
 */
public abstract class TimerEffect extends TimerTask{
    protected Entity myEntity;
    protected GameTimer myTimer;
    
    public TimerEffect(Entity entityToAffect){
        myEntity = entityToAffect;     
        myTimer = GameTimer.getInstance();
    }
}
