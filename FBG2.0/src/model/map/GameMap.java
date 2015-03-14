package model.map;

import model.Entity;

/**
 * This is a container of all the entities, items, tiles, and traps.
 * @author Hanif, ChrisMoscoso
 */
public class GameMap {
    private Locations<Entity> entities;
    private Locations<Item> items;
    private Tile[][] tiles;
    private Locations<Trap> entities;
    
    public GameMap(){
        Tile[][] t = new Tile[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                t[i][j] = new Tile();
            }
        }
        this.tiles = t;
    }
    
    public GameMap(Tile[][] tiles){
        this.tiles = tiles;
    }
    
    
}
