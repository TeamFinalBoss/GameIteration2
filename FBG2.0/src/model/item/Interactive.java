package model.item;

import model.gameObject.MapObject;
import model.link.ObstacleLink;
import model.map.pair.CoordinatePair;

/**
 * This class defines an Item which can be interacted with in-game
 * Extends Item abstract class
 * 
 * ID: 4
 * 
 * @see MapObject
 * @see Item
 * @author ashishag, Michael Cohen
 *
 */
public class Interactive extends Item {
    
        protected boolean hasBeenUsed;
        protected ObstacleLink link;
	
	public Interactive(){
		super("Generic Interactive", "Generic description", new CoordinatePair());
		
		this.setID("4");
		this.setClassName("Interactive");
        this.hasBeenUsed= false; 
        this.link = new ObstacleLink(this, 0);
		
		//Other properties set here
	}
	
	public Interactive(String objectName, String description, CoordinatePair location, 
                boolean hasBeenUsed){
		super(objectName, description, location);
		
		this.setID("4");
		this.setClassName("Interactive");
		this.link = new ObstacleLink(this, 0);

                this.hasBeenUsed = hasBeenUsed;
		
		//Other properties set here
	}
        
        //This is to check the status of item has been used
        
        public boolean getUsedStatus(){
            return hasBeenUsed;
            
        }
        
        //This is to change the status 
        
        public void setUsedStatus(boolean hasBeenUsed){
            this.hasBeenUsed = hasBeenUsed;
            
        }
        
        public void setLink(int newLink) {
        	this.link = new ObstacleLink(this, newLink);
        }
        
        //Try to use item and returns if it ables to use items successfully 
        
        public boolean use(){
            if (hasBeenUsed== true){
                return false;
            }
            
            else{
                hasBeenUsed = true;
                return true;
            }
        }
        
        
        
}
