/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.director.ActiveMapManager;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 * ID= 14
 * @author ashishjuit
 */
public class Stick extends Takeable {
    
	
	public Stick(){
		super("stick", "Generic description", new CoordinatePair(), 0, 1);
		
		this.id = "14";
		this.className = "Stick";
		
		//Other properties set here
	}
	
	public Stick(String objectName, String description, CoordinatePair location, int value, int durability){
		super(objectName, description, location, value, durability);
		
		this.id = "14";
		this.className = "Stick";
                this.value=value;
		
		//Other properties set here
	}
    
        @Override
	public Takeable copy() {
		return new Stick(this.getName(), this.getDescription(), this.getLocation(), this.getValue(), this.getDurability());
	}
        
         public Stick(int durability){
            super("stick", "Generic description", new CoordinatePair(), 0, durability);
        }
         
         @Override 
        public boolean useInSack(Entity e){
           return true;
            }
           
        }
        
   
       

