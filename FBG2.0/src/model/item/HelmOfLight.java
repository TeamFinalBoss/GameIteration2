/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.map.pair.CoordinatePair;
;

/**
 * ID= 18;
 * @author ashishag
 */
public class HelmOfLight extends Equipable {
     
    
   public HelmOfLight(){
		super("Generic Eqipable", "Generic description", 
                        new CoordinatePair(), 0, 1, EquipSlot.HEAD);
		
		this.id = "18";
		this.className = "HelmOfLight";
                
                
		
		//Other properties set here
	}
	
	public HelmOfLight(String objectName, String description, 
                CoordinatePair location, int value, EquipSlot slot){
		super(objectName, description, location, value, 1, EquipSlot.HEAD);
		
		this.id = "18";
		this.className = "HelmOfLight";
                this.slot= slot; 
               
		
		//Other properties set here
	} 
        
         @Override
         public boolean meetsRequirements(){
            return true;
         }
       
        public boolean useInSack(){
            return true;
        
            
        }
    
        public Takeable copy() {
    		return new HelmOfLight(this.getName(), this.getDescription(), this.getLocation(), this.getValue(), this.getSlot());
    	}
}
