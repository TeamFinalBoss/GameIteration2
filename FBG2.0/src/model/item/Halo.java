/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.entity.Entity;
import model.map.pair.CoordinatePair;
;

/**
 * ID= 18;
 * @author ashishag
 */
public class Halo extends Equipable {
     
    
   public Halo(){
		super("halo", "Generic description", 
                        new CoordinatePair(), 0, 1, EquipSlot.MAIN_HAND);
		
		this.id = "18";
		this.className = "HelmOfLight";
                
                
		
		//Other properties set here
	}
	
	public Halo(String objectName, String description, 
                CoordinatePair location, int value,  EquipSlot slot){
		super(objectName, description, location, value, 1, EquipSlot.HEAD);
		
		this.id = "18";
		this.className = "HelmOfLight";
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
        
        public Halo(int durability){
            super("halo", "Generic_description", 
                        new CoordinatePair(), 0, 1,EquipSlot.MAIN_HAND);
                        
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

        
        /*
        @Override 
        public boolean useInSack(Entity e){
            if (!meetsRequirements(e)){
                return false;
            }
            else{
           e.equip(this);
           return true;
            }
            
        }*/
        
    @Override
        public boolean meetsRequirements(Entity e){
        return e.getLevel() >= 1;
        
        }
        
   
}
