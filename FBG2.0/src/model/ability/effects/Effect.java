
package model.ability.effects;

import java.util.TimerTask;
import model.entity.Entity;

/**
 * Changes an Entity based on it's stored playerStats (or other, if necessary)
 * @author Jason Owens
 */
public class Effect{
    protected Entity myEntity;
    
    public Effect(Entity entityToAffect){
        myEntity = entityToAffect;        
    }
}
