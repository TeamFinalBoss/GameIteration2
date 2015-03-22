/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.effect.GoDownEffect;
import model.map.pair.CoordinatePair;

/**
 * ID=27;
 * @author ashishag
 */
public class Staff extends Weapon {
    public Staff(){
		super("Generic Mace", "Generic description", new CoordinatePair(), 
                        0, 1, EquipSlot.HEAD, new GoDownEffect(20));
		
		this.id = "27";
		this.className = "Staff";
                this.slot= EquipSlot.HEAD;
                
                
		
		//Other properties set here
	}
	
	public Staff(String objectName, String description, CoordinatePair location, int value, 
                int durability){
		super(objectName, description, location, value, durability, 
                        EquipSlot.HEAD, new GoDownEffect(20) );
		
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
            super("Staff", "Generic_description", new CoordinatePair(),
                0, durability, EquipSlot.HEAD, new GoDownEffect(10));
        }
    
    
}
