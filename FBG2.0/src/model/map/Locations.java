package model.map;

import model.gameObject.MapObject;
import model.map.pair.Pair;
import model.map.pair.CoordinatePair;

import java.util.ArrayList;

/**
 * 
 * The purpose of this class is to provide a wrapper for an ArrayList
 * which provides certain manipulation and iteration strategies needed
 * for the game map
 *
 * @author Michael Cohen
 * @version 1.1
 * @see GameMap
 */
public class Locations<S extends MapObject> {

    ArrayList<S> locations;

    public Locations() {
        locations = new ArrayList<S>();
    }


    /**
     * Gets the object contained somewhere on the map based on its
     * CoordinatePair If the CoordinatePair provided is not present, return null
     *
     * @param pair location of the object which is queried
     * @return object located at pair on the map, or null if Object isn't on the
     * map
     */
    public S getObjectAt(CoordinatePair pair) {
        for (S obj : locations) {
            if (pair.equals(obj.getLocation())) {
                return obj;
            }
        }

        return null;//Jason changed this. Returning null is more useful and isn't dangerous.
        //throw new RuntimeException("CoordinatePair not contained in Locations");
    }
    
    /**
     * Adds an object to the locations collection
     * 
     * @param obj to be stored in the locations collection
     * @author Michael Cohen
     */
    public void addObject(S obj){
    	locations.add(obj);
    }
    
    /**
     * Adds an object to the locations collection
     *
     * @param pair location of the object to be stored
     * @param obj to be stored in the locations collection
     */
    public void addObject(CoordinatePair pair, S obj) {
    	obj.setLocation(pair);
        locations.add(obj);
    }
    
    /**
     * Adds an object to the locations collection
     * May be unnecessary
     * 
     * @param x coordinate
     * @param y coordinate
     * @param obj to be stored in the locations collection
     */
    public void addObject(int x, int y, S obj){
    	obj.setLocation(new CoordinatePair(x, y));
    	locations.add(obj);
    }

    /**
     * Removes and returns the object contained at pair on the map. If the
     * CoordinatePair provided is not present, a runtime exception is thrown
     *
     * @param pair location of the object which is queried
     * @return obj that was removed from the Map
     */
    public S remove(CoordinatePair pair) {
        for (int i = 0; i != locations.size(); ++i) {
            S obj = locations.get(i);
            if (pair.equals(obj.getLocation())) {
                locations.remove(i);
                return obj;
            }
        }

        throw new RuntimeException("CoordinatePair not contained in Locations");

    }

    /*** 
     * Removes the provided object from the collection and returns its
     * CoordinatePair location If the object provided is not present, a runtime
     * exception is thrown
     *
     * @param obj to be removed from the collection
     */
    public void remove(S obj) {
        for (int i = 0; i != locations.size(); ++i) {
            S toRemove = locations.get(i);
            if (obj.equals(toRemove)){
            	locations.remove(i);
            	return;
            }
        }

        throw new RuntimeException("Object not contained in Locations");
    }
    
    /**
     * Gets the number of objects that there is.
     * @return the number of objects there are 
     */
    public int getSize() {
        return locations.size();
    }
}
