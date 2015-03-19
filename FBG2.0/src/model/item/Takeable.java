package model.item;

import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;
import model.entity.Entity;

/**
 * This class defines an Item which can be taken and added to the Inventory
 * Extends Item abstract class
 * 
 * ID: 3
 * 
 * @see MapObject
 * @see Item
 * @author ashishag, Michael Cohen
 *
 */
public abstract class Takeable extends Item {
    
    protected int value;
    protected int durability; 
	
	private int durability;
	private int value;
	
	public Takeable(){
		super("Generic Takeable", "Generic description", new CoordinatePair());
		
		this.id = "3";
		this.className = "Takeable";
                this.value= 0; 
                this.durability=1;
		
		this.durability = 1;
		
		//Other properties set here
	}
	
	public Takeable(String objectName, String description, CoordinatePair location, int value, int durability){
		super(objectName, description, location);
		
		this.id = "3";
		this.className = "Takeable";
                this.value=value;
                this.durability=durability;

        }

        public Takeable(String objectName, String description, int value, int durability){
		super(objectName, description, new CoordinatePair());
		
		this.id = "3";
		this.className = "Takeable";
                this.value=value;
                this.durability=durability;
		
		this.durability = durability;
		
		//Other properties set here
	}

        public int getValue(){
            return value; 
        }
        
        public void setValue(int value){
            this.value= value;
        }
        
        public boolean useInSack(Entity target){
        	//Check if Entity meets prerequisites for using the item
        	//If prerequisites were met, use the item and return true
        	//Else return false
        	return true;
        }
}
