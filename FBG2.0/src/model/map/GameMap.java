package model.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import model.map.tile.Tile;
import model.map.tile.Trap;
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

    private ArrayList<Entity> entityList;
    private Tile[][] tiles;

    public GameMap() {
        Tile[][] t = new Tile[50][50];
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) {
                t[i][j] = new Tile();
            }
        }

        this.tiles = t;
        
        entityList = new ArrayList<Entity>();
    }

    public GameMap(Tile[][] tiles) {
        this.tiles = tiles;
        
        entityList = new ArrayList<Entity>();
    }

    
    
    public void addEntity(Entity e){
        entityList.add(e);
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
        Object[] objects = {tiles, entityList};
        notifyObservers(objects);
    }

    @Override //Same as super but we want to update the view immediately when the view gets added
    public void addObserver(Observer o) {
        super.addObserver(o);
        updateView();
    }

    public void moveGameObjects() {
        for(Entity e: entityList){
            e.move();
            if(e.getLocation().x > this.getWidth() - 1){
                e.getLocation().x = this.getWidth() - 1;
            }else if(e.getLocation().x < 0){
                e.getLocation().x = 0;
            }
            
            if(e.getLocation().y > this.getHeight() - 1){
                e.getLocation().y = this.getHeight() - 1;
            }else if(e.getLocation().y < 0){
                e.getLocation().y = 0;
            }
        }
    }
}
