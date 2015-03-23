/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.link.ObstacleLink;
import model.map.pair.CoordinatePair;

/**
 * ID=21;
 * @author ashishag
 */
public class TreasureChest extends Interactive {
    public TreasureChest(){
		super("treasureChest", "Generic description", 
                        new CoordinatePair(), false);
		
		this.id = "21";
		this.className = "TreasureChest";
                
                
		
		//Other properties set here
	}
	
	public TreasureChest(String objectName, String description, CoordinatePair location, 
                boolean hasBeenUsed){
		super(objectName, description, location, hasBeenUsed);
		
		this.setID("4");
		this.setClassName("Interactive");
		this.link = new ObstacleLink(this, 0);

               
                
               
		
		//Other properties set here
	} 
        
     
         public boolean meetsRequirements(){
            return true;
         }
       
        public boolean useInSack(){
            return true;
        
            
        }
        
        

    
    public TreasureChest copy() {
        return new TreasureChest (this.getName(), this.getDescription(), 
                        this.getLocation(), false);
    }
    
    
    
    
}
