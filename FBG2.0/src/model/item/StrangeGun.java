/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.effect.DealDamageEffect;
import model.effect.Effect;

import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 * ID=29
 * @author ashishag
 */
public class StrangeGun extends Weapon{
    
     public StrangeGun(){
		super("Gun", "Generic description", new CoordinatePair(), 0, 1, 
                        EquipSlot.HEAD, new  DealDamageEffect(80));
		
		this.id = "29";
		this.className = "StrangeGun";
                this.E1= new  DealDamageEffect(80); 
             
                
		
		//Other properties set here
	}
	
	public StrangeGun(String objectName, String description, CoordinatePair 
                location, int value, int durability, EquipSlot slot, Effect E1){
		super(objectName, description, location, value, durability, slot, E1);
		
		this.id = "29";
		this.className = "StrangeGun";	
                
                this.durability=durability;
		//Other properties set here
	} 
        
        
    @Override
        public void attack(Entity e){
            E1.applyEffect(e);
        }
        
        
    @Override
          public Effect getEffect(){
            return E1; 
        }
        
    @Override
        public void setEffect(Effect E1){
            this.E1= E1;
        }
        
    @Override
        public boolean canSee(int observationSkill){
            return false; 
        }
        
    @Override
        public Takeable copy() {
    		return new Gun(this.getName(), this.getDescription(), 
                        this.getLocation(), this.getValue(), 
                        this.getDurability(), this.getSlot(), this.getEffect());
    	}
        
        
        public StrangeGun(int durability){
            super("Gun", "Generic description", new CoordinatePair(), 0, durability , 
                        EquipSlot.HEAD, new  DealDamageEffect(80));
        }
        //TODO- Projectile motion has to be added
    
      
        
    @Override
        public void onUnequip(Entity target){
            target.modifyAgility(-10);
            target.modifyWeaponOffense(-10);
        }
        
    /**
     *
     * @param e
     */
    @Override
    public void onEquip(Entity e){
            e.modifyAgility(10);
            e.modifyWeaponOffense(10);
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
        return e.getLevel() >= 2;
        }
        
        public void bullet(Entity e){
            
        }
    
}
