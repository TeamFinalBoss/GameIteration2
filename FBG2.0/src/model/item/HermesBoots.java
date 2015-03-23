/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 * ID=24
 * @author ashishag
 */
public class HermesBoots extends Equipable{
    public HermesBoots(){
		super("hermesBoots", "Generic description", new CoordinatePair(), 
                        0, 1, EquipSlot.FEET);
		
		this.id = "24";
		this.className = "HermesBoots";
              
    
                
		
		//Other properties set here
	}
	
	public HermesBoots(String objectName, String description, CoordinatePair location, int value, 
                int durability, EquipSlot slot){
		super(objectName, description, location, value, durability, slot);
		
		this.id = "24";
		this.className = "HermesBoots";
                this.slot= slot; 
                this.durability=durability;
		
		//Other properties set here
	} 
        @Override
        public EquipSlot getSlot(){
            return slot; 
        }
        @Override
        public void setslot (EquipSlot slot){
            this.slot= slot;
        }
        
       
        // for Armours 
       
        public boolean meetsRequirements(){
            return false;
        }
        
        @Override
    public Takeable copy(){
        return new HermesBoots(this.getName(), this.getDescription(), 
                this.getLocation(), this.getValue(), this.getDurability(), 
                this.getSlot());
    }
    
    
   public HermesBoots(int durability){
            super("hermesBoots", "Generic description", new CoordinatePair(), 
                        0, 1, EquipSlot.FEET);
}
   @Override
        public void onUnequip(Entity target){
            target.modifyAgility(-5);
            target.modifyWeaponOffense(-5);
        }
        
   @Override
        public void onEquip(Entity target){
            target.modifyAgility(5);
            target.modifyWeaponOffense(5);
        }
   public boolean useInSack(Entity target){
   	target.equip(this);
   	return true;
   }
        
        
    
}
