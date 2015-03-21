/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.effect.DealDamageEffect;
import model.map.pair.CoordinatePair;

/**
 * ID=20;
 * @author ashishag
 */
public class Sword extends Weapon {
    
     //durability
     public Sword(){
		super("Sword", "Generic_description", new CoordinatePair(),
                0, 1, EquipSlot.HEAD, new DealDamageEffect(50));
                this.id = "20";
		this.className = "Sword";
                
                
             
                
		
		//Other properties set here
	}
	
	public Sword(String objectName, String description, CoordinatePair 
                location, int durability ){
		super(objectName, description, location, 5, durability, 
                EquipSlot.HEAD, new DealDamageEffect(50) );
                
 
		
		this.id = "20";
		this.className = "Sword";	
                
		//Other properties set here
	} 
    
        public Takeable copy() {
		return new Sword(this.getName(), this.getDescription(), this.getLocation(), this.getDurability());
	}
        
        public void Sword(int durability){
            Sword("Sword", "Generic_description", new CoordinatePair(), 5, durability, 
                EquipSlot.HEAD, new DealDamageEffect(50));
        }
      
}


