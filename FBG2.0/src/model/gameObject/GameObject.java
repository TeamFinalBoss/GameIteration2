package model.gameObject;

import java.awt.Image;
import java.awt.Point;
import model.factory.SpriteFactory;



public class GameObject {
	protected int id;
	private String name;
	private String description;
        private String spritePath = null;
	
	
	//Added this, necessary to save game state. 
	private Point location;
	
	
	//This constructor should be called only after the subclass constructor is called
	public GameObject(){
		name = "Generic Object";
		description = "Generic description";
		location = new Point(1, 1); // default constructor, (0 , 0)
		
	}
	
	public GameObject(String className, String objectName, String description , int ID){
		//this.id = -1;	//should always be overriden
		this.name = objectName;
		this.description = description;
		this.id = ID;
		location = new Point(1, 1);
	}
	
        
        //Get's the game objects current state. By default the return value is 0. 
        /*
        * Each game object that actually has multiple states should override
        * this method and use logic to return different states. The int return 
        * value should be specified in a a public enum in that game object 
        * called States
        */
        public int getState(){return 0;}
	public int getID(){ return id; }
	public void setID(int id) {this.id = id;}
	public String getDescription(){ return description; }
	public String getName() { return name; }
	public Point getLocation() { return location; };
	public void setLocation(int x, int y) { location = new Point(x,y); };
        
        public Image getSprite(){
            return SpriteFactory.getSprite(spritePath);
        }
        
        public void setSpritePath(String spritePath){
            this.spritePath = spritePath;
        }
}
