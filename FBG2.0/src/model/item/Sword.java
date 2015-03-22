/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.effect.GoDownEffect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 * ID=20;
 * @author ashishag
 */
public class Sword extends Weapon {
    
     //durability
     public Sword(){
		super("Sword", "Generic_description", new CoordinatePair(),
                0, 1, EquipSlot.HEAD, new GoDownEffect(50));
                this.id = "20";
		this.className = "Sword";
                
                
             
                
		
		//Other properties set here
	}
	
	public Sword(String objectName, String description, CoordinatePair 
                location, int durability ){
		super(objectName, description, location, 5, durability, 
                EquipSlot.HEAD, new GoDownEffect(50) );
                
 
		
		this.id = "20";
		this.className = "Sword";	
                
		//Other properties set here
	} 
    
     @Override
        public Takeable copy() {
		return new Sword(this.getName(), this.getDescription(), this.getLocation(), this.getDurability());
	}
        
        public Sword(int durability){
            super("Sword", "Generic_description", new CoordinatePair(), 5, durability, 
                EquipSlot.HEAD, new GoDownEffect(50));
        }
        
        @Override
        public void onUnequip(Entity target){
            target.modifyAgility(-20);
            target.modifyWeaponOffense(-10);
        }
        
   @Override
        public void onEquip(Entity target){
            target.modifyAgility(20);
            target.modifyWeaponOffense(10);
        }
      @Override 
        public boolean useInSack(Entity e){
            if (!meetsRequirements(e)){
                return false;
            }
            else{
           e.equip(this);
           return true;
            }
            
        }
        
    @Override
        public boolean meetsRequirements(Entity e){
        return e.getLevel() >= 1 && e.getBargain()>=5 && e.getAgility()>=5;
        }
}


