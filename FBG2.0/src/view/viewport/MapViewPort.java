package view.viewport;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.map.GameMap;
import model.map.tile.Tile;

/**
 *
 * @author ChrisMoscoso
 */
public class MapViewPort implements ViewPort, Observer {

    Tile[][] tiles;
    int widthInTiles = 0, heightInTiles = 0;

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < widthInTiles; i++) {
            for (int j = 0; j < heightInTiles; j++) {
                try {
                    //Draw Tiles
                    //TODO: Make it so it doesnt just draw grass
                    BufferedImage img = ImageIO.read(new File("src/resources/grass.jpg"));
                    //g.drawImage(img, i * 64, j * 64, 64, 64, null);
                    g.drawRect(i*64, j*64, 32, 32);
                } catch (IOException ex) {}
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        tiles = (Tile [][]) arg;
        widthInTiles = tiles.length;
        heightInTiles = tiles[0].length;
    }

}
