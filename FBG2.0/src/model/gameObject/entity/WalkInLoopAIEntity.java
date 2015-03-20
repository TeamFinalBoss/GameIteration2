
package model.gameObject.entity;

import model.factory.SpriteFactory;
import model.map.Direction;

/**
 *
 * @author ChrisMoscoso
 */
public class WalkInLoopAIEntity extends Entity {
    
    private Direction[] path;
    private int currentStep = 0;
    
    public WalkInLoopAIEntity(){
        this.setSpritePath(SpriteFactory.RAT);
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
        location.translate(path[currentStep % path.length].dx * super.myStats.getSpeed(), path[currentStep % path.length].dy * super.myStats.getSpeed());
        currentStep++;
    }
}
