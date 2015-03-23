package model.map.tile.trap;

import model.entity.Entity;
import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;

/**
 * The purpose of this abstract class is to have an object that has a unique effect happen
 * when touched by an entity. In addition, it is possible for an entity to remove this
 * object from the game map.
 * Extends GameObject abstract class
 * 
 * ID: 7
 * 
 * @see MapObject
 * @author Aidan Pace, Michael Cohen
 */
public abstract class Trap extends MapObject{

	private int difficulty;
	
	Trap(){
		super("Generic Trap", "Generic description", new CoordinatePair());
	
		this.setID("7");
		this.setClassName("Trap");
		
		//Other properties set here
		this.difficulty = 0;
	}
	
	Trap(String objectName, String description, CoordinatePair location, 
			int difficulty){
		super(objectName, description, location);
		
		this.setID("7");
		this.setClassName("Trap");
		
		//Other properties set here
		this.difficulty = difficulty;
	}
	

	
	/**
	 * Determines if a trap was successfully disarmed.
	 * 
	 * @author Aidan Pace
	 * @param CallerLevel the trap disarming skill level of the entity attempting to disarm this trap
	 * @return whether or not the skill level was good enough
	 */
	public boolean attemptDisarm(int CallerLevel){
		if(CallerLevel >= difficulty) return true;
		return false;
	}
	
    /**
     * This method arbitrarily describes whether an entity can
     * 'observe' this area effect
     * 
     * @author Michael Cohen
     * @param observation skill needed to see the item
     * @return true if observation >= 50
     */
    public boolean canSee(Entity e){
    	return true;
    }
	
	/**
	 * Method to be overridden by subclasses to provide unique functionality
	 * when the trap is touched.
	 * 
	 * @author Aidan Pace
	 * @param caller The entity that activated the trap
	 * @see Entity
	 */
	public abstract void Activate(Entity caller);

    public void activate(Entity caller) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}