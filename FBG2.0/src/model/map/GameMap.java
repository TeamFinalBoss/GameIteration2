package model.map;

import java.util.Observable;
import java.util.Observer;
import model.map.tile.Tile;
import model.map.tile.Trap;
import model.map.pair.CoordinatePair;
import model.entity.Entity;
import model.item.Item;

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
    private Tile[][] tiles;
    private Locations<Trap> traps;

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
        this.addEntity(Entity.getPlayer(), new CoordinatePair(1, 1));
        this.traps = new Locations<>();
    }

    public GameMap(Tile[][] tiles) {
        this.tiles = tiles;
        this.items = new Locations<>();
        this.entities = new Locations<>();
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
            this.entities.addObject(CP, e);
            updateView();
            return true;
        } else {
            return false;
        }
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
            this.items.addObject(CP, item);
            updateView();
            return true;
        } else {
            return false;
        }
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
            this.traps.addObject(CP, trap);
            updateView();
            return true;
        } else {
            return false;
        }
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

    public void moveGameObjects() {
        //for()
    }
}
