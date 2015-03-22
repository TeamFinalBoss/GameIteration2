package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import model.director.ActiveMapManager;
import model.director.AvatarInteractionManager;
import model.director.GameDirector;
import model.entity.Entity;
import model.map.Locations;
import model.map.pair.CoordinatePair;
import model.map.tile.Tile;

/**
 *
 * @author ChrisMoscoso
 */
public class MapViewPort implements ViewPort, Observer {
    Entity avatar;
    Tile[][] tiles;
    Locations entities, items, traps;

    int widthInTiles = 0, heightInTiles = 0;
    BufferedImage grass;
    BufferedImage avatarImage;
    private int tileWidth = 64;
    private int tileHeight = 64;

    public MapViewPort() {
        try {
            grass = ImageIO.read(new File("src/resources/img/grass.jpg"));            
            avatarImage = ImageIO.read(new File("src/resources/img/summonerUp.gif"));
        } catch (IOException ex) {

        }
    }

    @Override
    public void draw(Graphics g) {
    	int windowWidth = (int) (GameDirector.getSize().width * .8);
    	int windowHeight = (int) (GameDirector.getSize().height * .8);
    	int windowWidthInTiles = windowWidth/ tileWidth;
    	int windowHeightInTiles = windowHeight/ tileHeight;
    	int startX = avatar.getLocation().getX() - windowWidthInTiles / 2;
    	int startY = avatar.getLocation().getY() - windowHeightInTiles / 2;
    	
    	if( startX < 0) {
    		startX = 0;
    	} else if (startX > widthInTiles - windowWidthInTiles) {
    		startX = widthInTiles- windowWidthInTiles;
    	}
    	if( startY < 0) {
    		startY = 0;
    	} else if (startY > heightInTiles - windowHeightInTiles) {
    		startY = heightInTiles- windowHeightInTiles;
    	}
    	
        for (int i = startX; i < Math.min(startX+windowWidthInTiles,widthInTiles); i++) {
            for (int j = startY; j < Math.min(startY+windowHeightInTiles,heightInTiles); j++) {
                //draw coordinates
            	g.setColor(Color.BLUE);
            	String coordinate = "(" + i + "," + j + ")";
            	int strX = (i - startX) * tileWidth + tileWidth / 2 - g.getFontMetrics().stringWidth(coordinate) / 2;
            	int strY = (j - startY) * tileHeight + tileHeight / 2;
            	//System.out.println("strx: " + strX + " stry: " + strY);
            	g.drawString(coordinate, strX, strY);

            	//Draw tile
                //TODO: Make it so it doesnt just draw grass       
                g.drawImage(grass, i * 64, j * 64, 64, 64, null);
            	
                //Draw enitty
                if (entities.getObjectAt(new CoordinatePair(i, j)) != null  ){
                	if(entities.getObjectAt(new CoordinatePair(i, j)).equals(avatar)) {
                		g.setColor(Color.blue);
                	} else {
                		g.setColor(Color.red);
                	}
                    g.drawImage(avatarImage, (i-startX)*64, (j-startY)*64, 64, 64, null);
                    //g.drawRect((i-startX)*64, (j-startY)*64, 63, 63);
                }
                
                
        
            }
            
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update is called");
        
        Object[] mapObjects = (Object[]) arg;
        tiles = (Tile[][]) mapObjects[0];
        widthInTiles = tiles.length;
        heightInTiles = tiles[0].length;
        
        avatar =(Entity) mapObjects[1];
        

        entities = (Locations) mapObjects[2];

        items = (Locations) mapObjects[3];
        traps = (Locations) mapObjects[4];

    }
}
