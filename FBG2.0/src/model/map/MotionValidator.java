package model.map;

import model.director.ActiveMapManager;
import model.entity.Entity;
import model.entity.MotionType;
import model.item.Item;
import model.item.Obstacle;
import model.map.pair.CoordinatePair;
import model.map.tile.Tile;

/**
 * TODO figure out how to check if an object is an obstacle
 * Used to validate movement
 * @author Jason Owens
 * 
*/
public class MotionValidator {
    private static MotionValidator validator = null;
    
    public MotionValidator(){
    }
    
    public static MotionValidator getInstance() {
            if(validator == null) {
                    validator = new MotionValidator();
            }
            return validator;
    }
    /**
     * checks terrain of desired tile and checks for obstacles
     * @author Jason Owens
     * @param entityMotion
     * @param itemAtDesiredLocation
     * @param tileMotion
     * @param entityToMove 
     * @return boolean whether or not the Entity can move in the direction it's facing (change Entity Direction before calling)
     */
    public boolean canTraverse(MotionType entityMotion, Item itemAtDesiredLocation, Tile t) {
        if(t == null){
            return false;
        }
        
        MotionType mt = t.getMotionType();
        
        
        if(!correctMotionType(entityMotion,mt)){ //checking terrain
            return false;
        }

        return true;
    }
    /**
     * checks terrain of desired tile and checks for obstacles
     * @author Jason Owens
     * @param entityMotion 
     * @return boolean whether or not the Entity's MotionType is good enough for the terrain
     */  
    private boolean correctMotionType(MotionType entityMotion, MotionType terrainMotion){
        switch(entityMotion) {
            case GROUND:
                    switch(terrainMotion) {
                            case GROUND:
                                    return true;
                            case WATER:
                                    return false;
                            case UNATTAINABLE:
                                    return false;
                    }

                    return false;
            case WATER:
                    switch(terrainMotion) {
                            case GROUND:
                                    return true;
                            case WATER:
                                    return true;
                            case UNATTAINABLE:
                                    return false;
                    }

                    return false;
            case UNATTAINABLE:
                    return false;
        }
        return false; //shouldn't ever reach this
    }
}