package model.item;


import model.entity.Entity;
import model.map.pair.CoordinatePair;
/**
 * ID=15
 * @author ashishag
 */

public abstract class Equipable extends Usable {
	
    protected EquipSlot slot;
    
   public Equipable(){
		super("Generic Eqipable", "Generic description", new CoordinatePair(), 0, 1);
		
		this.id = "15";
		this.className = "Equipable";
                this.slot= EquipSlot.HEAD;
                
		
		//Other properties set here
	}
	
	public Equipable(String objectName, String description, CoordinatePair location, int value, 
                int durability, EquipSlot slot){
		super(objectName, description, location, value, durability);
		
		this.id = "15";
		this.className = "Equipable";
                this.slot= slot; 
                this.durability=durability;
		
		//Other properties set here
	} 
    
        public EquipSlot getSlot(){
            return slot; 
        }
        
        public void setslot (EquipSlot slot){
            this.slot= slot;
        }
        
       
        // for Armours 
        public boolean meetsRequirements(Entity target){
            return false;
        }
        
        public abstract void onUnequip(Entity target); //undo the effects that were applied when the item was equipped
        public abstract void onEquip(Entity target); //carry out the effects that are to happen when this item is equipped to this entity
}
