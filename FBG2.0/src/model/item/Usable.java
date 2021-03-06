/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 * ID=13
 * @author ashishjuit
 */
public abstract class Usable extends Takeable{
    
  	protected EquipSlot slot;
	public Usable(){
		super("Generic Usable", "Generic description", new CoordinatePair(), 0, 1);
		
		this.id = "13";
		this.className = "Usable";
               
		
		//Other properties set here
	}
	
	public Usable(String objectName, String description, CoordinatePair location, int value, int durability){
		super(objectName, description, location, value, durability);
		
		this.id = "13";
		this.className = "Usable";
                this.durability=durability; 
               
		
		//Other properties set here
	}
        
       @Override
        public boolean useInSack(Entity target){
                target.unequip(this.slot);
        	target.equip((Equipable)this);
        	return true;
        }
}


