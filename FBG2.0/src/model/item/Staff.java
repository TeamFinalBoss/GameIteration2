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
 * ID=27;
 * @author ashishag
 */
public class Staff extends Weapon {
    public Staff(){
		super("staff", "Generic description", new CoordinatePair(), 
                        0, 1, EquipSlot.MAIN_HAND, new  DealDamageEffect(20));
		
		this.id = "27";
		this.className = "Staff";
                this.slot= EquipSlot.MAIN_HAND;
                
                
		
		//Other properties set here
	}
	
	public Staff(String objectName, String description, CoordinatePair location, int value, 
                int durability){
		super(objectName, description, location, value, durability, 
                        EquipSlot.MAIN_HAND, new  DealDamageEffect(20) );
		
		this.id = "27";
		this.className = "Staff";
               
                this.durability=durability;
		
		//Other properties set here
	} 
    
    @Override
        public Takeable copy() {
    		return new Mace(this.getName(), this.getDescription(), 
                        this.getLocation(), this.getValue(), 
                        this.getDurability());
    	}
        
        
        @Override
        public boolean canSee(int observationSkill){
            return true;
            
        }
        
         public Staff(int durability){
            super("staff", "Generic_description", new CoordinatePair(),
                0, durability, EquipSlot.MAIN_HAND, new  DealDamageEffect(10));
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
        return e.getLevel() >= 1 && e.getBargain()>=5 && e.getAgility()>=5;
        }
    
}
