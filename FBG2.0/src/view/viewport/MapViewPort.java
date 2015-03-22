package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import model.director.GameDirector;
import model.gameObject.entity.Avatar;
import model.gameObject.entity.Entity;
import model.gameObject.projectile.Projectile;
import model.map.tile.Tile;

/**
 *
 * @author ChrisMoscoso
 */
public class MapViewPort implements ViewPort, Observer {

    private int windowWidth, windowHeight;
    private final int tileWidth = 64, tileHeight = 64;

    Tile[][] tiles;

    private ArrayList<Entity> entityList;
    private ArrayList<Projectile> projectileList;

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
        int windowWidth = (int) (GameDirector.getSize().width * 0.8);
        int windowHeight = (int) (GameDirector.getSize().height * 0.8);
        //g.drawRect(0, 0, windowWidth, windowHeight);
        
        
        int windowWidthInTiles = ( windowWidth / tileWidth);
        int windowHeightInTiles = ( windowHeight / tileHeight);

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
            for (int j = startY; j < Math.min(startY + windowHeightInTiles, heightInTiles); j++) {

                //Draw Coordinates
                g.setColor(Color.blue);
                String coordinate = "(" + i + "," + j + ")";
                int strX = (i - startX) * tileWidth + tileWidth / 2 - g.getFontMetrics().stringWidth(coordinate) / 2;
                int strY = (j - startY) * tileHeight + tileHeight / 2;
                g.drawString(coordinate, strX, strY);

                //Draw Entities
                for (Entity e : entityList) {
                    if (e.getLocation().equals(new Point(i, j))) {
                        Image entityImg = e.getSprite();
                        g.drawImage(entityImg, (i - startX) * tileWidth, (j - startY) * tileHeight, null);

                        //Draw Entity Health Bars for all entities - avatar
                        if (!e.equals(Avatar.getAvatar())) {
                            double percentageOfHealth = (double) e.getPlayerStats().getCurrentHealth() / (double) e.getPlayerStats().getMaxHealth();
                            g.setColor(Color.gray);
                            g.fillRoundRect((i - startX) * tileWidth, (j - startY) * tileHeight, tileWidth, 3, 5, 5);
                            g.setColor(Color.green);
                            g.fillRoundRect((i - startX) * tileWidth, (j - startY) * tileHeight, (int) (tileWidth * percentageOfHealth), 3, 5, 5);
                        }
                    }
                }

                //Draw Projectiles
                try {
                    for (Projectile p : projectileList) {
                        if (p.getLocation().equals(new Point(i, j))) {
                            Image entityImg = p.getSprite();
                            g.drawImage(entityImg, (i - startX) * tileWidth, (j - startY) * tileHeight, null);
                        }
                    }
                } catch (ConcurrentModificationException e) {
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Object[] mapObjects = (Object[]) arg;
        tiles = (Tile[][]) mapObjects[0];
        widthInTiles = tiles.length;
        heightInTiles = tiles[0].length;

        ArrayList<Entity> e = (ArrayList<Entity>) mapObjects[1];
        entityList = e;

        ArrayList<Projectile> p = (ArrayList<Projectile>) mapObjects[2];
        projectileList = p;

    }
}
