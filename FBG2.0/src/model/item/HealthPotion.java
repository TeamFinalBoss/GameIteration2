/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.entity.Entity;
import model.map.pair.CoordinatePair;
import model.effect.Effect;

/**
 * ID=16
 * @author ashishag
 */
public class HealthPotion extends Usable {
    
    
    public HealthPotion(){
		super("Generic HealthPotion", "Generic description", new CoordinatePair(), 5, 0);
		
		this.id = "16";
		this.className = "HealthPotion";
               
		
		//Other properties set here
	}
	
	public HealthPotion(String objectName, String description, CoordinatePair location, int value){
		super(objectName, description, location, value, 1);
		
		this.id = "16";
		this.className = "HealthPotion";
               
		
		//Other properties set here
	}
        // TODO: increase hp player stat
        @Override 
        public boolean useInSack(Entity e){
            //e.heal(12);
            Effect ee = new HealEffect(10);
            ee.applyEffect(e);
            return true;
        }
        
        public Takeable copy() {
    		return new HealthPotion(this.getName(), this.getDescription(), this.getLocation(), this.getValue());
    	}
        
        public void HealPotion(int durability){
            HealthPotion("Generic HealthPotion", "Generic description", 
             new CoordinatePair(), 1, durability);
        }
}
    