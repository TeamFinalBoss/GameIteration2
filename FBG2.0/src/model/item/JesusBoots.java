/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 * ID=23;
 * @author ashishag
 */
public class JesusBoots extends Equipable{
    public JesusBoots(){
		super("jesusBoots", "Generic description", new CoordinatePair(), 
                        0, 1, EquipSlot.FEET);
		
		this.id = "23";
		this.className = "JesusBoots";
              
    
                
		
		//Other properties set here
	}
	
	public JesusBoots(String objectName, String description, CoordinatePair location, int value, 
                int durability, EquipSlot slot){
		super(objectName, description, location, value, durability, slot);
		
		this.id = "23";
		this.className = "JesusBoots";
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
        
       
        
        
        @Override
    public Takeable copy(){
        return new JesusBoots(this.getName(), this.getDescription(), 
                this.getLocation(), this.getValue(), this.getDurability(), 
                this.getSlot());
    }
    
    
    public JesusBoots(int durability){
            super("jesusBoots", "Generic description", new CoordinatePair(), 
                        0, 1, EquipSlot.FEET);
    }
    
    @Override
        public void onUnequip(Entity target){
            target.modifyAgility(-3);
            target.modifyWeaponOffense(-3);
        }
        
   @Override
        public void onEquip(Entity target){
            target.modifyAgility(3);
            target.modifyWeaponOffense(3);
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
}
