package model.item;

import model.director.ActiveMapManager;
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
 * @author Ashish Aggarwal
 *
 */
public abstract class Takeable extends Item {
    
    protected int value;
    protected int durability; 
	
	
	
	
	public Takeable(){
		super("Generic Takeable", "Generic description", new CoordinatePair());
		
		this.setID("3");
		this.setClassName("Takeable");
                this.value= 0; 
                this.durability=1;
		
		this.durability = 1;
		
		//Other properties set here
	}
	
	public Takeable(String objectName, String description, CoordinatePair location, int value, int durability){
		super(objectName, description, location);
		
		this.setID("3");
		this.setClassName("Takeable");
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
        
    @Override
        public String getType(){
            return "Takeable";
        }
        
    
    public abstract Takeable copy();
        public abstract boolean useInSack(Entity target); //return true iff use is successful

        
    @Override
        public boolean activate(Entity e){
            e.insert(this);
            ActiveMapManager.getInstance().removeItemFromActiveMap(this);
            return true;
        }
}

