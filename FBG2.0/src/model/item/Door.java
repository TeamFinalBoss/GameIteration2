/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.map.pair.CoordinatePair;

/**
 * ID=12
 * @author ashishag
 */
public class Door extends Obstacle {
    
    protected boolean doorOpen;
    
    public Door(){
        super("door", "This is a door, bang on!", new CoordinatePair());
        
        this.id = "12";
        this.className = "Door";
                this.doorOpen= false; 
        
        //Other properties set here
    }
    
    public Door(String objectName, String description, CoordinatePair location, 
            boolean doorOpen){
        super(objectName, description, location);
        
        this.id = "12";
        this.className = "Door";
        this.doorOpen = doorOpen;
        
        //Other properties set here
    }
   
    public boolean getDoorOpen(){
            return doorOpen;
    }
    
    
    public void setDoorOpen(boolean doorOpen){
        this.doorOpen = doorOpen;
        
    }

    
}
