package model.item;



import model.map.pair.CoordinatePair;
/**
 * ID=15
 * @author ashishag
 */

public abstract class Equipable extends Usable {
	
    protected EquipSlot slot;
    
   public Equipable(){
		super("Generic Eqipable", "Generic description", new CoordinatePair(), 0);
		
		this.id = "15";
		this.className = "Equipable";
                this.slot= EquipSlot.HEAD;
                
		
		//Other properties set here
	}
	
	public Equipable(String objectName, String description, CoordinatePair location, int value, EquipSlot slot){
		super(objectName, description, location, value);
		
		this.id = "15";
		this.className = "Equipable";
                this.slot= slot; 
               
		
		//Other properties set here
	} 
    
        public EquipSlot getSlot(){
            return slot; 
        }
        
        public void setslot (EquipSlot slot){
            this.slot= slot;
        }
       
}
