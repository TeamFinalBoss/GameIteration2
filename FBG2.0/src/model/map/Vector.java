package model.map;

/**
 * maintains a direction. (Currently only used for projectiles, open for 
 * extension though, of course)
 * @author Jason Owens
 */
public class Vector {
    private double x;
    private double y;
    
    /*-----------Constructors-----------*/
    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Vector(){
        this.x = 0;
        this.y = 0;
    }
    public Vector(Direction d){
        switch(d){
            case North: this.x = 0;
                        this.y =1;
                            break;
                case NorthEast: 
                    this.x = 1;
                    this.y= 1;
                                break;
                case NorthWest: 
                    this.x = 1;
                    this.y= -1;
                                break;
                case South:     
                    this.x = 0;
                    this.y= -1;
                                break;
                case West:      
                    this.x = -1;
                    this.y= 0;
                                break;
                case East:      
                    this.x = 1;
                    this.y= 0;
                                break;
                case SouthEast: 
                    this.x = -1;
                    this.y= 1;
                                   break;
                case SouthWest: 
                    this.x = -1;
                    this.y= -1;
                                break;
                
        }
    }
    
    
    /*----------Accessors-----------*/
    public double getX(){
        return x;        
    }
    public double getY(){
        return y;        
    }
       
    /*---------Mutators---------*/
    public void set( double nextX, double nextY){
        this.x = nextX;
        this.y = nextY;
    }
    public void setX(double nextX){
        this.x = nextX;
    }
    public void setY(double nextY){
        this.y = nextY;
    }
    public void add(Vector V){
        this.x += V.getX();
        this.y += V.getY();
    }

    /**
     * 
     * @param addedX
     * @param addedY
     * @author Jason Owens
     */
    public void add(double addedX, double addedY){
        this.x += addedX;
        this.y += addedY;
    }
    public void addX(double addedX){
        this.x += addedX;    
    }
    public void addY(double addedY){
        this.y += addedY;
    }
    
    /**
     * @author Jason Owens
     */
    public void multiply(double numToMultiplyVectorBy){
        this.x *= numToMultiplyVectorBy;
        this.y *= numToMultiplyVectorBy;
    }
}
