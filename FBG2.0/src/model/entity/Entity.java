package model.entity;

import java.awt.Point;

import model.gameObject.MapObject;
import model.map.Direction;
import model.map.pair.CoordinatePair;

/** 
 * The class Entity defines a common type for all entities (beings) in the game. 
 * 
 * ID: 1
 * 
 * @see MapObject
 * @author Matthew Kroeze, Chris Moscoso, Michael Cohen
 * @version 1.1.0 2015-03-15
 */

public class Entity extends MapObject{
	private Inventory myInventory;
	private Occupation myOccupation;
    private Direction myDirection;
    private int speed;
    private Point location; 
       
    private static Entity player;
        
    public Entity(){
        myInventory = new Inventory(5);
        myOccupation = new Occupation();
    }

    
        
    
    public Entity(String objectName, String description, CoordinatePair location, 
    		Inventory inventory, Occupation occupation, Direction direction, int speed){
    	super(objectName, description, location);
    	
    	this.id = "1";
    	this.className = "Entity";
    	this.speed = 0;
    	
    	this.myOccupation = occupation;
    	this.myDirection = direction;
    	this.myInventory = inventory;

    }
    
    /**
     * Gets Direction
     * 
     * @author Matthew Kroeze
     * @return Direction of Entity
     */
    public Direction getDirection() {
        return myDirection;
    }

    /**
     * Sets Direction
     * 
     * @author Matthew Kroeze
     * @param direction to set 
     */
    public void setDirection(Direction direction) {
        myDirection = direction;
    }

    /**
     * Sets Speed
     * 
     * @author Matthew Kroeze
     * @param speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    
    
}
