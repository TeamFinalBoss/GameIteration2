/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.map.pair.CoordinatePair;

/**
 * Change the ID if required
 * ID=11
 *
 * @author ashishag
 */
public class DoorOpener extends Interactive {
    
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
    
   
   
    
    
    
}
