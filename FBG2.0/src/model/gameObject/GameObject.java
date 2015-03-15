package model.gameObject;


import model.map.pair.CoordinatePair;
/**
 * This class defines a GameObject Interface that is implemented by 
 * Entities, Items, AreaEffects, Decals, and Traps in order to have them maintain 
 * their own ID, name, class name, description, and location
 * @author Chris Moscoso, Michael Cohen
 *
 */

public abstract class GameObject {
	protected String id;
	protected String name;
	protected String className;
	protected String description;
	 
	protected CoordinatePair location;
	
	
	//This constructor should be called only after the subclass constructor is called
	protected GameObject(){
		id = "-1"; //will need to be changed later manually
		name = "Generic Object";
		className = "GameObject";
		description = "Generic description";
		location = new CoordinatePair(); // default constructor, (0 , 0)
		
	}
	
	protected GameObject(String objectName, String description, CoordinatePair location){
		this.id = "-1";	//should always be overriden
		this.name = objectName;
		this.className = "GameObject";
		this.description = description;
		this.location = location;
		
	}
	
	/**
	 * Gets ID
	 * 
	 * @author Chris Moscoso
	 * @return GameObject's ID
	 */
	public String getID(){ return id; }
	
	/**
	 * Sets ID
	 * 
	 * @author Chris Moscoso
	 * @param id the ID to be set
	 */
	public void setID(int id) {this.id = id;}
	
	/**
	 * Gets Class name
	 * 
	 * @author Chris Moscoso
	 * @return GameObject's Class name
	 */
	public String getClassName(){ return className; }
	
	/**
	 * Gets Description
	 * 
	 * @author Chris Moscoso
	 * @return GameObject's ID
	 */
	public String getDescription(){ return description; }
	
	/**
	 * Gets object's name (In-game name)
	 * 
	 * @author Chris Moscoso
	 * @return GameObject's In-game name
	 */
	public String getName() { return name; }
	
	/**
	 * Gets the CoordinatePair location of the object
	 * 
	 * @author Chris Moscoso
	 * @return CoordinatePair location of the object
	 */
	public CoordinatePair getLocation() { return location; };
	
	/**
	 * Sets the CoordinatePair location of the object
	 * 
	 * @author Chris Moscoso
	 * @param p the CoordinatePair to be set 
	 */
	public void setLocation(CoordinatePair p) { location = p; };
}
