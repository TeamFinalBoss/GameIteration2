package model.map.tile;

import model.ability.Effect;
import model.entity.Entity;
import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;

/**
 * This class defines an effect that can be applied to a Tile 
 * which will activate once an Entity steps on it.
 * Extends GameObject abstract class
 * 
 * ID: 8
 * 
 * @see MapObject
 * @author Michael Cohen
 *
 */
public abstract class AreaEffect extends MapObject {

    Effect effect;

    public AreaEffect() {
    	super("Generic AreaEffect", "Generic description", new CoordinatePair());
    	
    	this.id = "8";
    	this.className = "Area Effect";
    	
    	//Other properties set here
        effect = null;
    }


    public AreaEffect(String objectName, String description, CoordinatePair location,
    		Effect effect) {
    	super(objectName, description, location);
    	
    	this.id = "8";
    	this.className = "Area Effect";
    	
    	//Other properties set here
        this.effect = effect;
    }

    /**
     * Method to be overridden by subclasses to provide unique functionality to
     * each AreaEffect when touched.
     *
     * @author Michael Cohen
     * @param caller the entity that activated the trap
     * @see Entity
     */
    public abstract void activate(Entity caller);
}
