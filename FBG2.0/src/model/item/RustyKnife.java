/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.ability.effects.DealDamageEffect;
import model.ability.effects.Effect;
import model.map.pair.CoordinatePair;

/**
 * ID= 19;
 * @author ashishag
 */
public class RustyKnife extends Weapon{
    
    
    //durability
     public RustyKnife(){
		super("RustyKnife", "Generic description", new CoordinatePair(), 0, 1,
                 EquipSlot.HEAD, new DealDamageEffect(10));
                this.id = "19";
		this.className = "RustyKnife";
                
                
             
                
		
		//Other properties set here
	}
	
	public RustyKnife(String objectName, String description, CoordinatePair 
                location, int durability ){
		super(objectName, description, location, 5, durability, 
                EquipSlot.HEAD, new DealDamageEffect(10) );
                
 
		
		this.id = "19";
		this.className = "RustyKnife";	
                
		//Other properties set here
	} 
}
