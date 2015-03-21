/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.map.pair.CoordinatePair;

/**
 * ID=21;
 * @author ashishag
 */
public class TreasureChest extends Equipable {
    public TreasureChest(){
		super("Generic Eqipable", "Generic description", 
                        new CoordinatePair(), 0, 1, EquipSlot.HEAD);
		
		this.id = "21";
		this.className = "TreasureChest";
                
                
		
		//Other properties set here
	}
	
	public TreasureChest(String objectName, String description, 
                CoordinatePair location, int value, EquipSlot slot){
		super(objectName, description, location, value, 1, EquipSlot.HEAD);
		
		this.id = "21";
		this.className = "TreasureChest";
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
        
        public void TreasureChest(int durability){
            TreasureChest("Generic Eqipable", "Generic_description", 
                        new CoordinatePair(), 0, durability);
        }

    @Override
    public Takeable copy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
