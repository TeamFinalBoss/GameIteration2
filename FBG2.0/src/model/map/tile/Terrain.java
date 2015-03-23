package model.map.tile;

import model.entity.Entity;
import model.entity.MotionType;
import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;

/**
 *
 * ID: 12
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
        super(name, description, new CoordinatePair());
        this.motiontype = type;
        
		this.setID("12");
		this.setClassName("Terrain");

    }
    
   

    /**
     * 
     * @return 
     */
    public MotionType getMotiontype() {
        return motiontype;
    }
    
    
}
