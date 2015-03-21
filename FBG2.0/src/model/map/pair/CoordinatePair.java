package model.map.pair;


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
    	return getX() == CP.getX() && getY() == CP.getY();
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

    /**
    * @author Aaron Iglesias
    * calculate distance between two coordinate pairs
    * @param location1, location2
    * @return distance
    */
    public double getDistance(CoordinatePair location1, CoordinatePair location2)
    {
        int x1 = location1.getX();
        int y2 = location2.getY();

        int y1 = location1.getY();
        int x2 = location2.getX();
        
        double distance = pow(pow(x1 - x2, 2) + pow(y1 - y2, 2), 0.5);
        
        return distance;
    }
}
