/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 * ID=28;
 * @author ashishag
 */
    public class Hornes extends Equipable{
        
        public Hornes(){
		super("Generic Hornes", "Generic description", 
                        new CoordinatePair(), 0, 1, EquipSlot.HEAD);
		
		this.id = "28";
		this.className = "Hornes";
                
                
		
		//Other properties set here
	}
	
	public Hornes(String objectName, String description, 
                CoordinatePair location, int value,  EquipSlot slot){
		super(objectName, description, location, value, 1, EquipSlot.HEAD);
		
		this.id = "28";
		this.className = "Hornes";
                this.slot= slot; 
               
		
		//Other properties set here
	} 
        
      
         public boolean meetsRequirements(){
            return true;
         }
       
        public boolean useInSack(){
            return true;
        
            
        }
    
   @Override
        public Takeable copy() {
    		return new Halo(this.getName(), this.getDescription(), 
                        this.getLocation(), this.getValue(), this.getSlot());
    	}
        
        public Hornes(int durability){
            super("Generic Halo", "Generic_description", 
                        new CoordinatePair(), 0, 1,EquipSlot.HEAD );
                        
}
        
   @Override
        public void onUnequip(Entity target){
            target.modifyAgility(-10);
            target.modifyWeaponOffense(-5);
        }
        
   @Override
        public void onEquip(Entity target){
            target.modifyAgility(10);
            target.modifyWeaponOffense(5);
        }

        
        
        
    
}