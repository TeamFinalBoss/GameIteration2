/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.map;

/**
 * This is basically a clone of Coordinate pair, but for doubles.
 * Is there a better way to do this?
 * @author Jason Owens
 */
public class PreciseCoordinatePair {
    private double x;
    private double y;
    
    public PreciseCoordinatePair(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    //default constructor
    public PreciseCoordinatePair(){
    	this.x = 0;
    	this.y = 0;
    }
   

    public double getX(){
        return x;        
    }
    public double getY(){
        return y;        
    }
    
    public boolean equals(PreciseCoordinatePair CP){
    	if(getX() == CP.getX() && getY() == CP.getY()) return true;
    	return false;
    }
    
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
    public void add(PreciseCoordinatePair CP){
        this.x += CP.getX();
        this.y += CP.getY();
    }
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


}
