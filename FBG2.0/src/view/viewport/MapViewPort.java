package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import model.map.Locations;
import model.map.pair.CoordinatePair;
import model.map.tile.Tile;

/**
 *
 * @author ChrisMoscoso
 */
public class MapViewPort implements ViewPort, Observer {

    Tile[][] tiles;
    Locations entities, items, traps;

    int widthInTiles = 0, heightInTiles = 0;
    BufferedImage grass;

    public MapViewPort() {
        try {
            grass = ImageIO.read(new File("src/resources/img/grass.jpg"));
        } catch (IOException ex) {

        }
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < widthInTiles; i++) {
            for (int j = 0; j < heightInTiles; j++) {
                //Draw tile
                //TODO: Make it so it doesnt just draw grass       
                g.drawImage(grass, i * 64, j * 64, 64, 64, null);
/*
                //Draw enitty
                if (entities.getObjectAt(new CoordinatePair(i, j)) != null  ){
                    g.setColor(Color.blue);
                    g.drawRect(i*64, j*64, 63, 63);
                }
                */
                //Entity.getPlayer().getLocation();
        
            }
            
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Object[] mapObjects = (Object[]) arg;
        tiles = (Tile[][]) mapObjects[0];
        widthInTiles = tiles.length;
        heightInTiles = tiles[0].length;

        entities = (Locations) mapObjects[1];

        items = (Locations) mapObjects[2];
        traps = (Locations) mapObjects[3];

    }
}
