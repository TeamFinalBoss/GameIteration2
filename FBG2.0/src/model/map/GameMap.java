package model.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import model.director.CombatCoordinator;
import model.gameObject.GameObject;
import model.map.tile.Tile;
import model.gameObject.entity.Entity;
import model.gameObject.projectile.Projectile;

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

    private ArrayList<GameObject> gameObjList;
    private ArrayList<Entity> entityList;
    private ArrayList<Projectile> projectileList;
    private Tile[][] tiles;

    public GameMap() {
        Tile[][] t = new Tile[50][50];
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) {
                t[i][j] = new Tile();
            }
        }

        this.tiles = t;

        entityList = new ArrayList<>();
        projectileList = new ArrayList<>();
        gameObjList = new ArrayList<>();
    }

    public GameMap(Tile[][] tiles) {
        this.tiles = tiles;

        entityList = new ArrayList<Entity>();
    }

    public void addEntity(Entity e) {
        entityList.add(e);
        gameObjList.add(e);
    }

    public void addProjectile(Projectile p) {
        projectileList.add(p);
        gameObjList.add(p);
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
        Object[] objects = {tiles, entityList, projectileList};
        notifyObservers(objects);
    }

    @Override //Same as super but we want to update the view as soon as the view gets added as an Observer
    public void addObserver(Observer o) {
        super.addObserver(o);
        updateView();
    }

    /**
     * Regenerates all the entities on the map.
     */
    public void regenerateEntities() {
        for (Entity e : entityList) {
            e.regenerate();
        }
    }
    
    
    /**
     * 
     */
    public void removeEntity(Entity e){
        entityList.remove(e);
    }

    /**
     * Moves all the game objects to move.
     */
    public void moveGameObjects() {
        for (Entity e : entityList) {
            e.move();
            //boundEntity(e);//use this to bound entity at edge
            warpEntity(e);// use this to warp the entity across edges
        }

        for (Iterator<Projectile> iterator = projectileList.iterator(); iterator.hasNext();) {
            Projectile p = iterator.next();
            p.move();
            if (isOutsideMap(p)) {
                iterator.remove();
            }
        }
    }

    /**
     * Removes the projectile from the map when it reaches the edge of the map.
     *
     * @param p the projectile to check
     * @return
     */
    public boolean isOutsideMap(Projectile p) {
        return p.getLocation().x < 0
                || p.getLocation().x > this.getWidth() - 1
                || p.getLocation().y > this.getHeight() - 1
                || p.getLocation().y < 0;
    }

    /**
     * Does not let the entity pass the edge of the map.
     *
     * @param e the entity to bound
     */
    public void boundEntity(Entity e) {
        if (e.getLocation().x > this.getWidth() - 1) {
            e.getLocation().x = this.getWidth() - 1;
        } else if (e.getLocation().x < 0) {
            e.getLocation().x = 0;
        }
        if (e.getLocation().y > this.getHeight() - 1) {
            e.getLocation().y = this.getHeight() - 1;
        } else if (e.getLocation().y < 0) {
            e.getLocation().y = 0;
        }
    }

    /**
     * If the entity passes the edge of the map warp him to the opposite edge.
     *
     * @param e the entity to warp
     */
    public void warpEntity(Entity e) {
        if (e.getLocation().x > this.getWidth() - 1) {
            e.getLocation().x = 0;
        } else if (e.getLocation().x < 0) {
            e.getLocation().x = this.getWidth() - 1;
        }
        if (e.getLocation().y > this.getHeight() - 1) {
            e.getLocation().y = 0;
        } else if (e.getLocation().y < 0) {
            e.getLocation().y = this.getHeight() - 1;
        }
    }

    public void checkProjectiles() {
        CombatCoordinator.checkCollideProjectiles(projectileList, entityList);
    }

}
