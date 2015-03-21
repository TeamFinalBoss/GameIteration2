package model.item;

import model.entity.Entity;
import model.gameObject.MapObject;
import model.link.ObstacleLink;
import model.map.pair.CoordinatePair;

/**
 * This class defines an Item which serves as an obstacle in-game 
 * Extends Item abstract class
 * 
 * ID: 5
 * 
 * @see MapObject
 * @see Item
 * @author ashishag, Michael Cohen
 *
 */
public class Obstacle extends Item {
	
	protected ObstacleLink link;
	
	public Obstacle(){
		super("Generic Obstacle", "Generic description", new CoordinatePair());
		
		this.setID("5");
		this.setClassName("Obstacle");
		this.link = new ObstacleLink(this, 0);

		
		//Other properties set here
	}
	
	public Obstacle(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.setID("5");
		this.setClassName("Obstacle");
		this.link = new ObstacleLink(this, 0);
		
		//Other properties set here
	}
	
	public void setLink(int newLink) {
		this.link = new ObstacleLink(this, newLink);
	}
        
        
        // This is called when the entity activates the item
        // returns if the entity can continue
        // by default entity cannot continue on obstacle
        @Override
        public boolean activate(Entity e){
            return false;
        }
}