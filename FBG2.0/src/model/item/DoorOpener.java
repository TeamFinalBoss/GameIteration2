/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import java.util.List;

import model.director.ActiveMapManager;
import model.director.AvatarInteractionManager;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 * Change the ID if required
 * ID=11
 *
 * @author ashishag
 */
public class DoorOpener extends Interactive { //opens all visible doors
    public DoorOpener(){
        super("doorOpener","You can open the door", new CoordinatePair(), false);
        this.id = "11";
        this.className = "DoorOpener";
    }
    
    public DoorOpener(String objectName, String description, CoordinatePair location, 
        boolean hasBeenUsed){
        super(objectName, description, location, hasBeenUsed);
        
    this.id = "11";
    this.className = "DoorOpener";
               
 
        
    }
    
    public boolean activate(Entity e){
    	List<Item> potentialDoors = AvatarInteractionManager.getInstance().getAvatar().getVisibleItems();
    	for(Item potentialDoor : potentialDoors){
    		if(potentialDoor.getClassName().equals("Door")) ActiveMapManager.getInstance().removeItemFromActiveMap(potentialDoor);
    	}
    	return false;
    }
    
   
   
    
    
    
}
