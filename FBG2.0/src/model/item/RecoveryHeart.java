/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.effect.GoUpEffect;
import model.effect.Effect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 * ID=22; 
 * @author ashishag
 */
public class RecoveryHeart extends OneShot {
    public RecoveryHeart(){
		super("Generic RecoveryHeart", "Generic description", new CoordinatePair());
		
		this.id = "22";
		this.className = "RecoveryHeart";
		
		//Other properties set here
	}
	
	public RecoveryHeart(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.id = "22";
		this.className = "RecoveryHeart";
		
		//Other properties set here
	}
    
        
        //TODO; Making the heart stats change and healed 
        
        
        public void healHeart(Entity w){
              Effect w1 = new GoUpEffect(10);
               w1.applyEffect(w);
           
            
        }
        
        public RecoveryHeart(int durability){
           super("Generic RecoveryHeart", "Generic description", new CoordinatePair());
        }
        
        
        public RecoveryHeart copy() {
    		return new RecoveryHeart (this.getName(), this.getDescription(), 
                        this.getLocation());
    	}
}
