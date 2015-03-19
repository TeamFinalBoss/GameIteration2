/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.entity.Entity;
import model.map.pair.CoordinatePair;
import model.ability.effects.Effect;
/**
 * ID=16
 * @author ashishag
 */
public class HealthPotion extends Usable {
    
    
    public HealthPotion(){
		super("Generic HealthPotion", "Generic description", new CoordinatePair(), 0);
		
		this.id = "16";
		this.className = "HealthPotion";
               
		
		//Other properties set here
	}
	
	public HealthPotion(String objectName, String description, CoordinatePair location, int value){
		super(objectName, description, location, value);
		
		this.id = "16";
		this.className = "HealthPotion";
               
		
		//Other properties set here
	}
        // TODO: increase hp player stat
        @Override 
        public void use(Entity e){
            //e.heal(12);
            Effect e = new HealEffect(10);
            e.applyEffect();
        }
       
}

