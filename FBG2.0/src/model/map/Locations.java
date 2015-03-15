package model.map;

import model.map.pair.Pair;
import model.map.pair.CoordinatePair;
import java.util.ArrayList;

/**
 * The purpose of this interface is to contain objects of type S as well as
 * their locations within the GameMap
 *
 * @author Michael Cohen
 *
 */
public class Locations<S> {

    ArrayList<Pair<S, CoordinatePair>> locations;

    public Locations() {
        locations = new ArrayList<Pair<S, CoordinatePair>>();
    }

    /**
     * Gets the CoordinatePair which represents the location of the contained
     * object. If the object provided is not present, a runtime exception is
     * thrown
     *
     * @author Michael Cohen
     * @param obj located somewhere in the map
     * @return CoordinatePair of location of obj
     */
    public CoordinatePair getCoordinatePair(S obj) {
        for (Pair<S, CoordinatePair> p : locations) {
            if (obj == p.getObject()) {
                return p.getCoordPair();
            }
        }
        throw new RuntimeException("Object not contained in Locations");
    }

    /**
     * Gets the object contained somewhere on the map based on its
     * CoordinatePair If the CoordinatePair provided is not present, a runtime
     * exception is thrown
     *
     * @param pair location of the object which is queried
     * @return object located at pair on the map, or null if Object isn't on the
     * map
     */
    public S getObjectAt(CoordinatePair pair) {
        for (Pair<S, CoordinatePair> p : locations) {
            if (pair.equals(p.getCoordPair())) {
                
                return p.getObject();
            }
        }

        return null;//Jason changed this. Returning null is more useful and isn't dangerous.
        //throw new RuntimeException("CoordinatePair not contained in Locations");
    }

    /**
     * Adds an object and CoordinatePair to the locations collection
     *
     * @param pair location of the object to be stored
     * @param obj to be stored in the locations collection
     */
    public void addObject(CoordinatePair pair, S obj) {
        locations.add(new Pair<>(obj, pair));
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
            Pair<S, CoordinatePair> p = locations.get(i);
            if (p.getCoordPair().equals(pair)) {
                locations.remove(i);
                return p.getObject();
            }
        }

        throw new RuntimeException("CoordinatePair not contained in Locations");

    }

    /**
     * Removes the provided object from the collection and returns its
     * CoordinatePair location If the object provided is not present, a runtime
     * exception is thrown
     *
     * @param obj to be removed from the collection
     * @return CoordinatePair location of the object on the map
     */
    public CoordinatePair remove(S obj) {
        for (int i = 0; i != locations.size(); ++i) {
            Pair<S, CoordinatePair> p = locations.get(i);
            if (p.getObject().equals(obj)) {
                locations.remove(i);
                return p.getCoordPair();
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
