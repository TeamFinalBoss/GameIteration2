package model.map;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.map.tile.AreaEffect;
import model.map.tile.Tile;
import model.map.tile.Trap;
import model.map.pair.CoordinatePair;
import model.entity.Avatar;
import model.entity.Entity;
import model.item.Interactive;
import model.item.Item;
import model.item.Obstacle;
import model.item.OneShot;
import model.item.Takeable;

/**
 * This is a container of all the entities, items, tiles, and traps. Currently,
 * of the four types of objects on the map only one object per type can occupy a
 * map. For example, there may be an entity and a trap on one tile, but there
 * are not two entities on one tile nor traps, nor items, etc.
 *
 * This GameMap extends Observable because it will be observed by the view. When
 * the map hasChanged it will only disclose essential, non-private information
 * to any observers currently observing the map.
 *
 * @author Hanif, ChrisMoscoso, Jason Owens
 */
public class GameMap extends Observable {

    private Locations<Entity> entities;
    private Locations<Item> items;
    private Locations<Trap> traps;
    private Locations<AreaEffect> effects;
    private ArrayList<Projectile> projectiles;
    
    private Tile[][] tiles;
    
    private MotionValidator MV;
    private MotionCoordinator MC;
    
    public GameMap() {
        Tile[][] t = new Tile[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                t[i][j] = new Tile();
            }
        }

        this.tiles = t;
        this.items = new Locations<>();
        this.entities = new Locations<>();
        this.effects = new Locations<>();
        
        this.addEntity(Avatar.getPlayer(), new CoordinatePair(1, 1)); //TODO change to avatar
        this.traps = new Locations<>();
    }

    public GameMap(Tile[][] tiles) {
        this.tiles = tiles;
        
        this.items = new Locations<>();
        this.entities = new Locations<>();
        this.effects = new Locations<>();
        
        this.addEntity(Avatar.getPlayer(), new CoordinatePair(1, 1)); //TODO change to avatar
        this.traps = new Locations<>();
    }

    /**
     * Adds an entity to the map at the valid coordinate pair specified if not
     * already occupied by another entity.
     *
     * @return true if entity was added to the map
     * @param e Entity to be added to the map
     * @param CP where the entity should be added
     */
    public final boolean addEntity(Entity e, CoordinatePair CP) {
        if (CoordPairIsValid(CP) && entities.getObjectAt(CP) == null) {
            this.entities.addObject(e, CP);
            updateView();
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * This method attempts to remove the provided entity
     * from the Entity Locations collection and returns
     * whether or not that removal was successful
     * 
     * @author Michael Cohen
     * @param e the entity to be removed
     * @return true if entity was removed, else false
     */
    public final boolean removeEntity(Entity e){
    	return this.entities.remove(e);
    }
    
    /**
     * Removes and returns the entity from the map. If 
     * the provided CoordinatePair is not present, a runtime exception is thrown 
     * 
     * @author Michael Cohen
     * @param CP location of the entity which is queried
     * @return Entity that was removed from the map
     */
    public Entity removeEntity(CoordinatePair CP){
    	return this.entities.remove(CP);
    }
    
    
    /**
     * Adds an Item to the map at the valid coordinate pair specified if not
     * already occupied by another item.
     *
     * @return true if item was added to the map
     * @param item the item to be added to the map
     * @param CP where the item is to be added
     */
    public final boolean addItem(Item item, CoordinatePair CP) {
        if (CoordPairIsValid(CP) && items.getObjectAt(CP) == null) {
            this.items.addObject(item, CP);
            updateView();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * This method attempts to remove the provided item
     * from the Item Locations collection and returns
     * whether or not that removal was successful
     * 
     * @author Michael Cohen
     * @param i the item to be removed
     * @return true if item was removed, else false
     */
    public final boolean removeItem(Item i){
    	return this.items.remove(i);
    }
    
    /**
     * Removes and returns the item from the map. If 
     * the provided CoordinatePair is not present, a runtime exception is thrown 
     * 
     * @author Michael Cohen
     * @param CP location of the item which is queried
     * @return Item that was removed from the map
     */
    public Item removeItem(CoordinatePair CP){
    	return this.items.remove(CP);
    }
    
    /**
     * Adds an area effect to the map at the valid coordinate pair specified if not
     * already occupied by another area effect
     * 
     * @author Michael Cohen
     * @param effect the area effect to be added to the map
     * @param CP where the area effect is to be added
     * @return true if effect was added to the map
     */
    public final boolean addAreaEffect(AreaEffect effect, CoordinatePair CP){
    	if (CoordPairIsValid(CP) && effects.getObjectAt(CP) == null){
    		this.effects.addObject(effect, CP);
    		updateView();
    		return true;
    	}
    	else return false;
    }    
    
    /**
     * This method attempts to remove the provided area effect
     * from the AreaEffect Locations collection and returns
     * whether or not that removal was successful
     * 
     * @author Michael Cohen
     * @param effect to be removed
     * @return true if effect was removed, else false
     */
    public final boolean removeAreaEffect(AreaEffect effect){
    	return this.effects.remove(effect);
    }
    
    /**
     * Removes and returns the area effect from the map. If 
     * the provided CoordinatePair is not present, a runtime exception is thrown 
     * 
     * @author Michael Cohen
     * @param CP location of the effect which is queried
     * @return AreaEffect that was removed from the map
     */
    public AreaEffect removeAreaEffect(CoordinatePair CP){
    	return this.effects.remove(CP);
    }
    
    /**
     * Adds a trap to the map at the valid coordinate pair specified if not
     * already occupied by another trap.
     *
     * @author Jason Owens
     * @return true if item was added to the map
     * @param trap the trap to be added to the map
     * @param CP where the trap is to be added
     */
    public final boolean addTrap(Trap trap, CoordinatePair CP) {
        if (CoordPairIsValid(CP) && traps.getObjectAt(CP) == null) {
            this.traps.addObject(trap, CP);
            updateView();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * This method attempts to remove the provided trap
     * from the Trap Locations collection and returns
     * whether or not that removal was successful
     * 
     * @author Michael Cohen
     * @param t trap to be removed
     * @return true if trap was removed, else false
     */
    public final boolean removeTrap(Trap t){
    	return this.traps.remove(t);
    }
    
    /**
     * Removes and returns the trap from the map. If 
     * the provided CoordinatePair is not present, a runtime exception is thrown 
     * 
     * @author Michael Cohen
     * @param CP location of the trap which is queried
     * @return Trap that was removed from the map
     */
    public Trap removeTrao(CoordinatePair CP){
    	return this.traps.remove(CP);
    }
    
    /**
     * Tests if the coordinate pair is non negative and not beyond the size of
     * the map
     *
     * @param CP the coordinate pair to be tested.
     * @return true if the coordinate pair is valid for the game map.
     */
    private boolean CoordPairIsValid(CoordinatePair CP) {
        int x = CP.getX(), y = CP.getY();
        if (x < 0 || y < 0) {
            return false;
        } else {
            return x < getWidth() && y < getHeight();
        }
    }

    /*------------Accessors--------------*/
    /**
     * Returns the Entity at the CoordinatePair
     *
     * @author Jason Owens
     * @return the tile at the CoordinatePair
     */
    public Entity getEntityAtCoordinate(CoordinatePair location) {
        return entities.getObjectAt(location);
    }
    
    /**
     * Returns the Tile at the CoordinatePair
     *
     * @author Jason Owens
     * @return the tile at the CoordinatePair
     */
    public Tile getTileAtCoordinate(CoordinatePair location) {
        return tiles[location.getX()][location.getY()];
    }
    /**
     * Returns the Item at the CoordinatePair
     *
     * @author Jason Owens
     * @return the tile at the CoordinatePair
     */
    public Item getItemAtCoordinate(CoordinatePair location) {
        return items.getObjectAt(location);
    }
    /**
     * Returns how wide the map is in number of tiles.
     *
     * @return the width of the map in tiles
     */
    public int getWidth() {
        return this.tiles.length;
    }

    /**
     * Returns how high the map is in number of tiles.
     *
     * @return the height of the map in tiles
     */
    public int getHeight() {
        return this.tiles[0].length;
    }

    /**
     * This should be called when you want to update the view objects observing
     * the map (in this case the MapViewPort). For the sake of encapsulation it
     * only passes the objects needed for drawing the view.
     */
    private void updateView() {
        setChanged();
        Object[] objects = {tiles, entities, items, traps};
        notifyObservers(objects);
    }

    @Override //Same as super but we want to update the view immediately when the view gets added
    public void addObserver(Observer o) {
        super.addObserver(o);
        updateView();
    }
    
    
    /*----------Mutators----------------*/

   
    public boolean requestMovement(Entity e, Direction dir){
        CoordinatePair desiredLocation; 
        desiredLocation = locationPlusDirection(e.getLocation(), dir);
        
        if(MV.canTraverse(e.getMotionType(), getItemAtCoordinate(desiredLocation), getTileAtCoordinate(desiredLocation).getMotionType())){
           MC.move(e, desiredLocation);
        }
        else{
            return false;
        }
    }
    
    /**
     * This is used by MotionValidator and MotionCoordinator to get the next location.
     * @author Jason Owens
     * @param initialLocation the location of the MapObject trying to move
     * @param direction the direction trying to move in
     * @return finalLocation a CoordinatePair of the new location given a direction
     */
    public static CoordinatePair locationPlusDirection(CoordinatePair initialLocation, Direction direction){
        CoordinatePair returnThis = new CoordinatePair(initialLocation.getX(),initialLocation.getY());
        switch(direction) {
            case North:
                returnThis.addY(-1); break;
            case NorthEast:
                returnThis.addY(-1);
                returnThis.addX(1);
                break;
            case NorthWest:
                returnThis.addY(-1);
                returnThis.addX(-1);
                break;
            case South:
                returnThis.addY(1);
                break;   
            case SouthEast:
                returnThis.addY(1);
                returnThis.addX(1);
                break;      
            case SouthWest:
                returnThis.addY(1);
                returnThis.addX(-1);
                break;      
            case West:
                returnThis.addX(-1); 
                break;    
            case East:
                returnThis.addX(-1); 
                break;  
          
        }
        return returnThis; 
    }
}
    
    
    
   

