package model.entity;

import java.awt.Point;

import model.gameObject.MapObject;
import model.map.Direction;
import model.map.pair.CoordinatePair;
import java.util.List;
import model.item.Takeable;
import model.item.Equipable;
import model.item.EquipSlot;
import java.util.Map;

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
        
    /* -------------------- CONSTRUCTORS --------------------*/
    public Entity(){
        myInventory = new Inventory(5, this);
        myOccupation = new Occupation();
    
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
    
    /* -------------------- INVENTORY ACCESSORS --------------------*/
    public List<Takeable> getSackContents(){
    	return myInventory.sackContents();
    }
    public int getSackSize(){
    	return myInventory.sackSize();
    }
    public int getSackCapacity(){
    	return myInventory.sackCapacity();
    }
    public Map<EquipSlot,Equipable> getArmoryContents(){
    	return myInventory.armoryContents();
    }
    
    /* -------------------- INVENTORY MUTATORS ---------------------*/
    public boolean use(int position){
    	return myInventory.use(position);
    }
    public Takeable remove(int position){
    	return myInventory.remove(position);
    }
    public boolean insert(Takeable next){
    	return myInventory.insert(next);
    }
    public void equip(Equipable next){
    	myInventory.equip(next);
    }
    public Equipable unequip(EquipSlot slot){
    	return myInventory.unequip(slot);
    }
    
    /* -------------------- PENDING SORTING ---------------------*/
    //TODO: Sort these operations into categories above
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
