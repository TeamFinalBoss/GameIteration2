package model.map.tile;

import model.entity.Entity;
import model.entity.MotionType;
import model.gameObject.MapObject;

/**
 *
 * @author ChrisMoscoso, Jason Owens
 */
public class Terrain extends MapObject {

    private MotionType motiontype;

    /**
     * Return grass terrain by default
     */
    public Terrain(){
        this("Grass", "Please Keep Off", MotionType.GROUND);
    }
    
    /**
     * 
     * @param name
     * @param description
     * @param type 
     */
    public Terrain(String name, String description, MotionType type){
        

    }

    /**
     * 
     * @return 
     */
    public MotionType getMotiontype() {
        return motiontype;
    }
    
    
}
