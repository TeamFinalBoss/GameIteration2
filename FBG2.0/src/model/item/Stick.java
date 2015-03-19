/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.map.pair.CoordinatePair;

/**
 * ID= 14
 * @author ashishag
 */
public class Stick extends Takeable {
    
	
	public Stick(){
		super("Stick", "Generic description", new CoordinatePair(), 0);
		
		this.id = "14";
		this.className = "Stick";
		
		//Other properties set here
	}
	
	public Stick(String objectName, String description, CoordinatePair location, int value){
		super(objectName, description, location, value);
		
		this.id = "14";
		this.className = "Stick";
                this.value=value;
		
		//Other properties set here
	}
    
}
