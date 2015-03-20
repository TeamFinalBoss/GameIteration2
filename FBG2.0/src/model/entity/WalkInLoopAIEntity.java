
package model.entity;

import model.map.Direction;

/**
 *
 * @author ChrisMoscoso
 */
public class WalkInLoopAIEntity extends Entity {
    
    private Direction[] path;
    private int currentStep = 0;
    
    public WalkInLoopAIEntity(){
        
        Direction[] simpleLoop = {Direction.East, Direction.East, Direction.South, Direction.South, Direction.West, Direction.West, Direction.North, Direction.North};
        path = simpleLoop;
    }
    
    /**
     * 
     * @param path 
     */
    public void setPath(Direction[] path){
        this.path = path;  
    }
    
    @Override
    public void move(){
        
        location.translate(path[currentStep % path.length].dx * speed, path[currentStep % path.length].dy * speed);
        //System.out.println()
        currentStep++;
    }
}
