package model.gameObject;


import model.ability.CoordinatePair;

public abstract class GameObject {
	protected int id;
	private String name;
	private String className;
	private String description;
	
	
	//Added this, necessary to save game state. 
	private CoordinatePair location;
	
	
	//This constructor should be called only after the subclass constructor is called
	protected GameObject(){
		id = 0; //will need to be changed later manually
		name = "Generic Object";
		className = "GameObject";
		description = "Generic description";
		location = new CoordinatePair(); // default constructor, (0 , 0)
		
	}
	
	protected GameObject(String className, String objectName, String description , int ID){
		//this.id = -1;	//should always be overriden
		this.name = objectName;
		this.className = className;
		this.description = description;
		this.id = ID;
		location = new CoordinatePair(); // default constructor, (0 , 0)
		
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
	public String getClassName(){ return className; }
	public String getDescription(){ return description; }
	public String getName() { return name; }
	public CoordinatePair getLocation() { return location; };
	public void setLocation(CoordinatePair p) { location = p; };
}
