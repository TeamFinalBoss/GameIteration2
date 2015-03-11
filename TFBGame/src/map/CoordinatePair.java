package map;

/**
 *
 * @author Jason Owens
 */
public class CoordinatePair {
    private int x;
    private int y;
    
    public CoordinatePair(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    //default constructor
    public CoordinatePair(){
    	this.x = 0;
    	this.y = 0;
    }

    public int getX(){
        return x;        
    }
    public int getY(){
        return y;        
    }
    
    public boolean equals(CoordinatePair CP){
    	if(getX() == CP.getX() && getY() == CP.getY()) return true;
    	return false;
    }
    
    public void set( int nextX, int nextY){
        this.x = nextX;
        this.y = nextY;
    }
    public void setX(int nextX){
        this.x = nextX;
    }
    public void setY(int nextY){
        this.y = nextY;
    }
    public void add(CoordinatePair CP){
        this.x += CP.getX();
        this.y += CP.getY();
    }
    public void add(int addedX, int addedY){
        this.x += addedX;
        this.y += addedY;
    }
    public void addX(int addedX){
        this.x += addedX;    
    }
    public void addY(int addedY){
        this.y += addedY;
    }
}
