package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import model.director.GameDirector;
import model.entity.Entity;
import model.map.tile.Tile;

/**
 *
 * @author ChrisMoscoso
 */
public class MapViewPort implements ViewPort, Observer {

    private final int tileWidth = 64, tileHeight = 64;

    Tile[][] tiles;
    
    private ArrayList<Entity> entityList;

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
        //Calculate which portion of the map to draw based on avatar position.
        int windowWidthInTiles = (GameDirector.getSize().width / tileWidth);
        int windowHeightInTiles = (GameDirector.getSize().height / tileHeight);

        int startX = entityList.get(0).getLocation().x - windowWidthInTiles / 2;
        int startY = entityList.get(0).getLocation().y - windowHeightInTiles / 2;

        if (startX < 0) {
            startX = 0;
        } else if (startX > widthInTiles - windowWidthInTiles) {
            startX = widthInTiles - windowWidthInTiles;
        }
        if (startY < 0) {
            startY = 0;
        } else if (startY > heightInTiles - windowHeightInTiles) {
            startY = heightInTiles - windowHeightInTiles;
        }

        //Start drawing
        for (int i = startX; i < Math.min(startX + windowWidthInTiles, widthInTiles); i++) {
            for (int j = startY; j < startY + windowHeightInTiles; j++) {
                //Draw 
                
                
                //Draw Entities
                if (entityList.get(0).getLocation().equals(new Point(i, j))) {
                    g.setColor(Color.red);
                    g.drawRect((i - startX) * tileWidth,
                            (j - startY) * tileHeight,
                            tileWidth,
                            tileHeight
                    );
                }
                g.setColor(Color.blue);
                String coordinate = "(" + i + "," + j + ")";
                int strX = (i - startX) * tileWidth + tileWidth / 2 - g.getFontMetrics().stringWidth(coordinate) / 2;
                int strY = (j - startY) * tileHeight + tileHeight / 2;
                g.drawString(coordinate, strX, strY);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Object[] mapObjects = (Object[]) arg;
        tiles = (Tile[][]) mapObjects[0];
        widthInTiles = tiles.length;
        heightInTiles = tiles[0].length;

        
        entityList = (ArrayList<Entity>) mapObjects[1];

    }
}
