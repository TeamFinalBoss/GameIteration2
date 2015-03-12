package model.factories;


import java.util.ArrayList;
import model.util.GameObject;
import org.w3c.dom.Element;

/**
 * The purpose of this interface is to define behaviors that all other factories should inherit from.
 * 
 * @author Aidan Pace
 */
public interface PlaceableObjectFactory {
	
    /**
    * To be overridden by classes that implement this interface.
    * The general purpose of this method is to search through a parsed XML document from the
    * given element and create objects accordingly, then return a list of them.
    * 
    * @author Aidan Pace
    * @param head the node to begin search at
    * @return A list of objects created by this method
    */
	public ArrayList<GameObject> generate(Element head);
}
