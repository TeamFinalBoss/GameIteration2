/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.effect.DealDamageEffect;
import model.entity.Entity;

import model.map.pair.CoordinatePair;

/**
 * ID= 19;
 * @author ashishag
 */
public class RustyKnife extends Weapon{
    
    
    //durability
     public RustyKnife(){
		super("RustyKnife", "Generic_description", new CoordinatePair(),
                0, 1, EquipSlot.HEAD, new  DealDamageEffect(10));
                this.id = "19";
		this.className = "RustyKnife";
                
                
             
                
		
		//Other properties set here
	}
	
	public RustyKnife(String objectName, String description, CoordinatePair 
                location, int durability ){
		super(objectName, description, location, 5, durability, 
                EquipSlot.HEAD, new  DealDamageEffect(10) );
                
 
		
		this.id = "19";
		this.className = "RustyKnife";	
                
		//Other properties set here
	}
	
     @Override
	public Takeable copy() {
		return new RustyKnife(this.getName(), this.getDescription(), this.getLocation(), this.getDurability());
	}
        
     @Override
        public boolean canSee(int observationSkill){
            return false; 
        }
        
        public RustyKnife(int durability){
            super("RustyKnife", "Generic_description", new CoordinatePair(),
                0, durability, EquipSlot.HEAD, new  DealDamageEffect(10));
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
        return e.getLevel() >= 1 && e.getCurrentHP()>=5 && e.getAgility()>=5;
        }
}
