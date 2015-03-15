package model.entity;

/** An Inventory object belongs to a singular Entity and holds the items of that entity.
 * @author Matthew Kroeze
 * @version 1.0.0 2015-03-14
 */
public class Inventory {
	Sack mySack;
	Armory myArmory;
	
	/** Creates an <code>Inventory</code> instance with a Sack component and an Armory component.
	 * @param sackCap the capacity of the <code>Sack</code> component of the <code>Inventory</code> 
	 */
	public Inventory(int sackCap){
		mySack = new Sack(sackCap);
		myArmory = new Armory();
	}
}
