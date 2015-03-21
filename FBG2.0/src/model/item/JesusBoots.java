/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.map.pair.CoordinatePair;

/**
 * ID=23;
 * @author ashishag
 */
public class JesusBoots extends Equipable{
    public JesusBoots(){
		super("JesusBoots", "Generic description", new CoordinatePair(), 
                        0, 1, EquipSlot.FEET);
		
		this.id = "23";
		this.className = "JesusBoots";
              
    
                
		
		//Other properties set here
	}
	
	public JesusBoots(String objectName, String description, CoordinatePair location, int value, 
                int durability, EquipSlot slot){
		super(objectName, description, location, value, durability, slot);
		
		this.id = "23";
		this.className = "JesusBoots";
                this.slot= slot; 
                this.durability=durability;
		
		//Other properties set here
	} 
        @Override
        public EquipSlot getSlot(){
            return slot; 
        }
        @Override
        public void setslot (EquipSlot slot){
            this.slot= slot;
        }
        
       
        // for Armours 
        public boolean meetsRequirements(){
            return false;
        }
        
        @Override
    public Takeable copy(){
        return new JesusBoots(this.getName(), this.getDescription(), 
                this.getLocation(), this.getValue(), this.getDurability(), 
                this.getSlot());
    }
    
    
    public void JesusBoots(int durability){
            JesusBoots("HermesBoots", "Generic description", new CoordinatePair(), 
                        0, 1, EquipSlot.FEET);
}
