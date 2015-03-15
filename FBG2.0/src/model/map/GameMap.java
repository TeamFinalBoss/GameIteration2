package model.map;

import model.Entity;
import model.item.Item;

/**
 * This is a container of all the entities, items, tiles, and traps.
 * @author Hanif, ChrisMoscoso, Jason Owens
 */
public class GameMap {
    private Locations<Entity> entities;
    private Locations<Item> items;
    private Tile[][] tiles;
    private Locations<Trap> traps;
    
    public GameMap(){
        Tile[][] t = new Tile[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                t[i][j] = new Tile();
            }
        }
        this.tiles = t;
        this.items = new Locations<>();
        this.entities = new Locations<>();
        this.traps = new Locations<>();
    }
    
    public GameMap(Tile[][] tiles){
        this.tiles = tiles;
        this.items = new Locations<>();
        this.entities = new Locations<>();
        this.traps = new Locations<>();
    }
    
    /*--------mutators-------*/
    
    /**
    * adds an Entity to the map. Includes checks for improper coordinates.
    * @author Jason Owens
    * @return whether CP is valid location
    * @param e Entity to be added to the map
    * @param CP where the entity should be added
    */
    public boolean addEntity(Entity e, CoordinatePair CP){
        int x = CP.getX();
        int y = CP.getY();
        if(x >= tiles.length || y >= tiles[x].length || x<0 || y<0){ //location is outside the game map
            return false;            
        }
        else if(entities.getObjectAt(CP) != null){ //location is occupied
            return false;
        }
        else{ //location is viable
            this.entities.addObject(CP, e);     
            return true;
        }
        
    }
    
    /**
    * adds an Item to the map. Includes checks for improper coordinates.
    * @author Jason Owens
    * @return whether CP is valid location
    * @param item the item to be added to the map
    * @param CP where the item is to be added
    */
    public boolean addItem(Item item, CoordinatePair CP){
        int x = CP.getX();
        int y = CP.getY();
        if(x >= tiles.length || y >= tiles[x].length || x<0 || y<0){
            return false;            
        }
        else if(items.getObjectAt(CP) != null){ //location is occupied
            return false;
        }
        else{ //location is viable
            return true;
        }
    }
    
    /**
    * adds an Item to the map. Includes checks for improper coordinates.
    * @author Jason Owens
    * @return whether CP is valid location
    * @param trap the trap to be added to the map
    * @param CP where the trap is to be added
    */
    public boolean addTrap(Trap trap, CoordinatePair CP){
        int x = CP.getX();
        int y = CP.getY();
        if(x >= tiles.length || y >= tiles[x].length || x<0 || y<0){
            return false;            
        }
        else if(traps.getObjectAt(CP) != null){ //location is occupied
            return false;
        }
        else{ //location is viable
            this.traps.addObject(CP, trap);  
            return true;
        }
    }
    
}
