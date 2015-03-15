package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.director.GameDirector;
import model.map.tile.Tile;

/**
 *
 * @author ChrisMoscoso
 */
public class MapViewPort implements ViewPort, Observer {

    Tile[][] tiles;
    int widthInTiles = 0, heightInTiles = 0;
    BufferedImage grass;

    public MapViewPort(){
        try {
            grass = ImageIO.read(new File("src/resources/grass.jpg"));
        } catch (IOException ex) {
            
        }
    }
    
    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < widthInTiles; i++) {
            for (int j = 0; j < heightInTiles; j++) {
                    //Draw Tiles
                    //TODO: Make it so it doesnt just draw grass
                    
                    g.drawImage(grass, i * 64, j * 64, 64, 64, null);
                    
                    
                    
                
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        tiles = (Tile [][]) arg;
        widthInTiles = tiles.length;
        heightInTiles = tiles[0].length;
    }

    private static class pubilc {

        public pubilc() {
        }
    }

}
