/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.effect.DealDamageEffect;
import model.map.pair.CoordinatePair;

/**
 * ID=26;
 * @author ashishag
 */
public class Mace extends Weapon {
    
    public Mace(){
		super("Generic Mace", "Generic description", new CoordinatePair(), 
                        0, 1, EquipSlot.HEAD, new DealDamageEffect(20));
		
		this.id = "26";
		this.className = "Mace";
                this.slot= EquipSlot.HEAD;
                
                
		
		//Other properties set here
	}
	
	public Mace(String objectName, String description, CoordinatePair location, int value, 
                int durability){
		super(objectName, description, location, value, durability, 
                        EquipSlot.HEAD, new DealDamageEffect(20) );
		
		this.id = "26";
		this.className = "Mace";
                this.slot= slot; 
                this.durability=durability;
		
		//Other properties set here
	} 
    
    @Override
        public Takeable copy() {
    		return new Mace(this.getName(), this.getDescription(), 
                        this.getLocation(), this.getValue(), 
                        this.getDurability());
    	}
        
        public void Mace(int durability){
            Mace("Generic Mace", "Generic description", new CoordinatePair(), 
                        0, durability, EquipSlot.HEAD, new DealDamageEffect(20));
        }
    
}
