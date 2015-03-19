/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.map.pair.CoordinatePair;
import model.entity.Entity;

/**
 * ID=13
 * @author ashishag
 */
public abstract class Usable extends Takeable{
    
  	
	public Usable(){
		super("Generic Usable", "Generic description", new CoordinatePair(), 0);
		
		this.id = "13";
		this.className = "Usable";
               
		
		//Other properties set here
	}
	
	public Usable(String objectName, String description, CoordinatePair location, int value){
		super(objectName, description, location, value);
		
		this.id = "13";
		this.className = "Usable";
               
		
		//Other properties set here
	}
        
        // This is for checking up the use with sack 
    
        public abstract void use(Entity e);
        
}


