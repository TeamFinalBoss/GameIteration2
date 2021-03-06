package model.map;

import model.map.projectiles.Projectile;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.map.areaEffect.AreaEffect;
import model.map.tile.Tile;
import model.map.tile.trap.Trap;
import model.map.pair.CoordinatePair;
import model.entity.Entity;
import model.item.Interactive;
import model.item.Item;
import model.item.Obstacle;
import model.item.OneShot;
import model.item.Takeable;
import model.director.ActiveMapManager;
import model.director.AvatarInteractionManager;

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
    private Locations<MapSwitcher> switchers;
    private ArrayList<Projectile> projectiles;

    private Tile[][] tiles;

    private MotionValidator MV;
    private MotionCoordinator MC;
    private int mapID;

    public GameMap() {
        Tile[][] t = new Tile[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                t[i][j] = new Tile();
            }
        }

        this.mapID = 1;
        this.tiles = t;
        this.items = new Locations<>();
        this.entities = new Locations<>();
        this.effects = new Locations<>();
        this.switchers = new Locations<>();
        this.traps = new Locations<>();
        this.projectiles = new ArrayList<>();
        this.MC = MotionCoordinator.getInstance();
        this.MV = MotionValidator.getInstance();
        //this.addEntity(AvatarInteractionManager.getInstance().getAvatar(), new CoordinatePair(1, 1)); //TODO change to avatar

    }
    
   
    public void clearObservers() {
    	super.deleteObservers();
    }

    public GameMap(int mapID) {
        Tile[][] t = new Tile[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                t[i][j] = new Tile();
            }
        }

        this.mapID = mapID;
        this.tiles = t;
        this.items = new Locations<>();
        this.entities = new Locations<>();
        this.effects = new Locations<>();
        this.switchers = new Locations<>();
        this.traps = new Locations<>();
        this.projectiles = new ArrayList<>();
        this.MC = MotionCoordinator.getInstance();
        this.MV = MotionValidator.getInstance();
    }

    public GameMap(Tile[][] tiles) {
        this.tiles = tiles;

        this.mapID = 1;
        this.items = new Locations<>();
        this.entities = new Locations<>();
        this.effects = new Locations<>();
        this.switchers = new Locations<>();
        this.traps = new Locations<>();
        this.projectiles = new ArrayList<>();
        this.MC = MotionCoordinator.getInstance();
        this.MV = MotionValidator.getInstance();

        //this.addEntity(AvatarInteractionManager.getInstance().getAvatar(), new CoordinatePair(1, 1)); //TODO change to avatar
    }

    /**
     * Adds an entity to the map at the valid coordinate pair specified if not
     * already occupied by another entity.
     *
     * TODO: Determine if boolean signature is necessary
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

    public void setID(int mapID) {
        this.mapID = mapID;
    }

    public int getID() {
        return mapID;
    }

    /**
     * This method attempts to remove the provided entity from the Entity
     * Locations collection and returns whether or not that removal was
     * successful
     *
     * TODO: Determine if boolean signature is necessary
     *
     * @author Michael Cohen
     * @param e the entity to be removed
     * @return true if entity was removed, else false
     */
    public final boolean removeEntity(Entity e) {
        return this.entities.remove(e);
    }

    /**
     * Removes and returns the entity from the map. If the provided
     * CoordinatePair is not present, a runtime exception is thrown
     *
     * @author Michael Cohen
     * @param CP location of the entity which is queried
     * @return Entity that was removed from the map
     */
    public Entity removeEntity(CoordinatePair CP) {
        return this.entities.remove(CP);
    }

    /**
     * Adds an Item to the map at the valid coordinate pair specified if not
     * already occupied by another item.
     *
     * TODO: Determine if boolean signature is necessary
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
     * This method attempts to remove the provided item from the Item Locations
     * collection and returns whether or not that removal was successful
     *
     * TODO: Determine if boolean signature is necessary
     *
     * @author Michael Cohen
     * @param i the item to be removed
     * @return true if item was removed, else false
     */
    public final boolean removeItem(Item i) {
        return this.items.remove(i);
    }

    /**
     * Removes and returns the item from the map. If the provided CoordinatePair
     * is not present, a runtime exception is thrown
     *
     * @author Michael Cohen
     * @param CP location of the item which is queried
     * @return Item that was removed from the map
     */
    public Item removeItem(CoordinatePair CP) {
        return this.items.remove(CP);
    }

    /**
     * Adds an area effect to the map at the valid coordinate pair specified if
     * not already occupied by another area effect
     *
     * TODO: Determine if boolean signature is necessary
     *
     * @author Michael Cohen
     * @param effect the area effect to be added to the map
     * @param CP where the area effect is to be added
     * @return true if effect was added to the map
     */
    public final boolean addAreaEffect(AreaEffect effect, CoordinatePair CP) {
        if (CoordPairIsValid(CP) && effects.getObjectAt(CP) == null) {
            this.effects.addObject(effect, CP);
            updateView();
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method attempts to remove the provided area effect from the
     * AreaEffect Locations collection and returns whether or not that removal
     * was successful
     *
     * TODO: Determine if boolean signature is necessary
     *
     * @author Michael Cohen
     * @param effect to be removed
     * @return true if effect was removed, else false
     */
    public final boolean removeAreaEffect(AreaEffect effect) {
        return this.effects.remove(effect);
    }

    /**
     * Removes and returns the area effect from the map. If the provided
     * CoordinatePair is not present, a runtime exception is thrown
     *
     * @author Michael Cohen
     * @param CP location of the effect which is queried
     * @return AreaEffect that was removed from the map
     */
    public AreaEffect removeAreaEffect(CoordinatePair CP) {
        return this.effects.remove(CP);
    }

    /**
     * Adds a switcher to the map at the valid coordinate pair specified if not
     * already occupied by another switcher.
     *
     * TODO: Determine if boolean signature is necessary
     *
     * @author Michael Cohen
     * @param switcher the switcher to be added to the map
     * @param CP where the switcher is to be added
     * @return true if switcher was added to the map
     */
    public final boolean addMapSwitcher(MapSwitcher switcher, CoordinatePair CP) {
        if (CoordPairIsValid(CP) && switchers.getObjectAt(CP) == null) {
            this.switchers.addObject(switcher, CP);
            updateView();
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method attempts to remove the provided switcher from the MapSwitcher
     * Locations collection and returns whether or not that removal was
     * successful
     *
     * TODO: determine if boolean signature is necessary
     *
     * @author Michael Cohen
     * @param switcher to be removed
     * @return true if switcher was removed, else false
     */
    public final boolean removeMapSwitcher(MapSwitcher switcher) {
        return this.switchers.remove(switcher);
    }

    /**
     * Removes and returns the switcher from the map. If the provided
     * CoordinatePair is not present, a runtime exception is thrown
     *
     * @param CP location of the switcher which is being queried
     * @return MapSwitcher that was removed from the map
     */
    public MapSwitcher removeMapSwitcher(CoordinatePair CP) {
        return this.switchers.remove(CP);
    }

    /**
     * Adds a trap to the map at the valid coordinate pair specified if not
     * already occupied by another trap.
     *
     * TODO: Determine if boolean signature is necessary
     *
     * @author Jason Owens
     * @return true if trap was added to the map
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
     * This method attempts to remove the provided trap from the Trap Locations
     * collection and returns whether or not that removal was successful
     *
     * TODO: Determine if boolean signature is necessary
     *
     * @author Michael Cohen
     * @param t trap to be removed
     * @return true if trap was removed, else false
     */
    public final boolean removeTrap(Trap t) {
        return this.traps.remove(t);
    }

    /**
     * Removes and returns the trap from the map. If the provided CoordinatePair
     * is not present, a runtime exception is thrown
     *
     * @author Michael Cohen
     * @param CP location of the trap which is queried
     * @return Trap that was removed from the map
     */
    public Trap removeTrap(CoordinatePair CP) {
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
        if (CoordPairIsValid(location)) {
            return tiles[location.getX()][location.getY()];
        } else {
            return null;
        }
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
     * @author Jason Owens
     * @param location
     * @return AreaEffect at Coordinate
     */
    public AreaEffect getAreaEffectAtCoordinate(CoordinatePair location) {
        return effects.getObjectAt(location);
    }

    /**
     * @author Jason Owens
     * @param location
     * @return MapSwitcher at Coordinate
     */
    public MapSwitcher getSwitcherAtCoordinate(CoordinatePair location) {
        return switchers.getObjectAt(location);
    }

    /**
     * @author Jason Owens
     * @param location
     * @return Trap at Coordinate
     */
    public Trap getTrapAtCoordinate(CoordinatePair location) {
        return traps.getObjectAt(location);
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

    public List<Entity> getEntities() {
        return entities.getList();
    }

    public List<Item> getItems() {
        return items.getList();
    }

    public List<AreaEffect> getAreaEffects() {
        return effects.getList();
    }

    public List<Trap> getTraps() {
        return traps.getList();
    }

    public List<MapSwitcher> getMapSwitchers() {
        return switchers.getList();
    }

    /**
     * This should be called when you want to update the view objects observing
     * the map (in this case the MapViewPort). For the sake of encapsulation it
     * only passes the objects needed for drawing the view.
     */
    private void updateView() {
        setChanged();
        Object[] objects = new Object[8];
        Entity avatar = AvatarInteractionManager.getInstance().getAvatar();

        objects[0] = tiles;
        objects[1] = avatar.getVisibleTiles();
        objects[2] = avatar.getLocation();
        objects[3] = avatar.getVisibleEntities();
        objects[4] = avatar.getVisibleItems();
        objects[5] = avatar.getVisibleTraps();
        objects[6] = avatar.getVisibleProjectiles();
        objects[7] = avatar.getVisibleAreaEffects();
        //objects = {tiles, avatar,entities, items, traps};
        notifyObservers(objects);
    }

    @Override //Same as super but we want to update the view immediately when the view gets added
    public void addObserver(Observer o) {
        super.addObserver(o);
        updateView();
    }

    /*----------Mutators----------------*/

    /*--------Object interaction----------*/
    public boolean requestMovement(Entity e, Direction dir) {
        CoordinatePair desiredLocation;
        desiredLocation = locationPlusDirection(e.getLocation(), dir);
        if (getEntityAtCoordinate(desiredLocation) != null) {
        	if(e == AvatarInteractionManager.getInstance().getAvatar()){
        		AvatarInteractionManager.getInstance().setConversationPartner(getEntityAtCoordinate(desiredLocation));
        		AvatarInteractionManager.getInstance().getConversationPartner().resetDialogue();
        	}
            return false;
        }

        if (MV.canTraverse(e.getMotionType(), getItemAtCoordinate(desiredLocation), getTileAtCoordinate(desiredLocation))) {
            MC.moveEntity(e, desiredLocation, getAreaEffectAtCoordinate(desiredLocation), getItemAtCoordinate(desiredLocation),
                    getSwitcherAtCoordinate(desiredLocation), getTrapAtCoordinate(desiredLocation));
            return true;
        } else {
            return false;
        }
    }

    public boolean useAbility(Entity e, int abilityToUse) {
        e.useAbility(abilityToUse);

        //I don't want to mess with entity
        return true;
    }

    /**
     * This is used by MotionValidator and MotionCoordinator to get the next
     * location.
     *
     * @author Jason Owens
     * @param initialLocation the location of the MapObject trying to move
     * @param direction the direction trying to move in
     * @return finalLocation a CoordinatePair of the new location given a
     * direction
     */
    public static CoordinatePair locationPlusDirection(CoordinatePair initialLocation, Direction direction) {
        CoordinatePair returnThis = new CoordinatePair(initialLocation.getX(), initialLocation.getY());
        switch (direction) {
            case North:
                returnThis.addY(-1);
                break;
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
                returnThis.addX(1);
                break;

        }
        return returnThis;
    }

    /**
     * @author Jason Owens
     * @param center
     * @param radius
     * @param containedTiles
     * @param containedProjectiles
     * @param containedEntities
     * @param containedTraps
     * @param containedItems
     * @param containedAreaEffects
     */
    public void getEverythingInRange(CoordinatePair center, int radius, List<Tile> containedTiles, List<Projectile> containedProjectiles,
            List<Entity> containedEntities, List<Trap> containedTraps, List<Item> containedItems, List<AreaEffect> containedAreaEffects) {

        int centerX = center.getX();
        int centerY = center.getY();

        int maxX = Math.min(center.getX() + radius, tiles.length - 1);
        int maxY = Math.min(center.getY() + radius, tiles[0].length - 1);

        int minX = Math.max(center.getX() - radius, 0);
        int minY = Math.max(center.getY() - radius, 0);

        int realMinX = center.getX() - radius;
        int realMinY = center.getY() - radius;
        int realMaxX = center.getX() + radius;
        int realMaxY = center.getY() + radius;

        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                //If we are in the radius and not one of the corners
                if ((!((i == realMinX && j == realMinY)
                        || (i == realMaxX && j == realMinY)
                        || (i == realMinX && j == realMaxY)
                        || (i == realMaxX && j == realMaxY)) || (radius <= 1))) {
                    containedTiles.add(tiles[i][j]);
                    if (getEntityAtCoordinate(new CoordinatePair(i, j)) != null) {
                        containedEntities.add(getEntityAtCoordinate(new CoordinatePair(i, j)));
                    }
                    if (getItemAtCoordinate(new CoordinatePair(i, j)) != null) {
                        containedItems.add(getItemAtCoordinate(new CoordinatePair(i, j)));
                    }
                    if (getTrapAtCoordinate(new CoordinatePair(i, j)) != null) {
                        containedTraps.add(getTrapAtCoordinate(new CoordinatePair(i, j)));
                    }
                    if (getAreaEffectAtCoordinate(new CoordinatePair(i, j)) != null) {
                        containedAreaEffects.add(getAreaEffectAtCoordinate(new CoordinatePair(i, j)));
                    }
                }
            }
        }

        try {

            for (Projectile p : projectiles) {
                int px = (int) p.getLocation().getX();
                int py = (int) p.getLocation().getY();

                //If we are in the radius and not one of the corners
                if ((px >= minX && px <= maxX && py >= minY && py <= maxY)
                        && !((px == realMinX && py == realMinY)
                        || (px == realMaxX && py == realMinY)
                        || (px == realMinX && py == realMaxY)
                        || (px == realMaxX && py == realMaxY) || (radius <= 1))) {
                    containedProjectiles.add(p);
                }
            }
        } catch (ConcurrentModificationException e) {
        }
    }
    //TODO: add to projectiles list and this method should be done

    public void addProjectile(Projectile proj) {
        projectiles.add(proj);
        updateView();

    }

    public void removeProjectile(Projectile proj) {
        projectiles.remove(proj);
        updateView();

        /*
         for(Projectile p: projectiles){
         if(p == proj){
                
         }
         }*/
    }
}
