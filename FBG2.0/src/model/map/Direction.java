
package model.map;

/**
 *
 * @author Jason
 */
public enum Direction {
    North (0,-1),
    NorthEast (1, 1),
    East (1,0),
    SouthEast (1,-1),
    South (0, 1),
    SouthWest (-1, -1),
    West (-1, 0),
    NorthWest (1, -1),
    None (0, 0);
    
    /**
     * This is the unit value for the horizontal displacement [-1, 0 ,1].
     */
    public final int dx,

    /**
     * This is the unit value for the vertical displacement [-1, 0, 1].
     */
    dy;
    
    Direction(int dx , int dy){
        this.dx = dx;
        this.dy = dy;
    }   
}