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
 * @author Ashish Aggarwal, Michael Cohen
 *
 */
public abstract class Takeable extends Item {
    
    protected int value;
    protected int durability; 
	
	
	
	
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



        public int getValue(){
            return value; 
        }
        
        public void setValue(int value){
            this.value= value;
        }
        
         public int getDurability(){
            return durability; 
        }
        
        public void setDurability(int durability){
            this.durability= durability;
        }
       
}
