package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.director.ActiveMapManager;
import model.director.AvatarInteractionManager;
import model.director.GameDirector;
import model.entity.Entity;
import model.map.Locations;
import model.map.projectiles.Projectile;
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
    ArrayList<Projectile> projectiles;

    int widthInTiles = 0, heightInTiles = 0;
    BufferedImage grass;
    private int tileWidth = 64;
    private int tileHeight = 64;
    
    ImageIcon avatarIcon;

    public MapViewPort() {
        try {
            grass = ImageIO.read(new File("src/resources/img/grass.jpg"));            
            //avatarImage = ImageIO.read(new File("src/resources/img/summonerUp.gif"));
                                avatarIcon = new ImageIcon("src/resources/img/summonerUp.gif");

        } catch (IOException ex) {

        }
    }

    @Override
    public void draw(Graphics g) {
        avatar = AvatarInteractionManager.getInstance().getAvatar();
        //Calculate which portion of the map to draw based on avatar position.
        int windowWidth = (int) (GameDirector.getSize().width * 0.8);
        int windowHeight = (int) (GameDirector.getSize().height * 0.8);
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
                //Draw tile
                //TODO: Make it so it doesnt just draw grass       
                //g.drawImage(grass, (i - startX) * tileWidth, (j - startY) * tileHeight, tileWidth, tileHeight, null);
                
                //draw coordinates
            	g.setColor(Color.BLUE);
            	String coordinate = "(" + i + "," + j + ")";
            	int strX = (i - startX) * tileWidth + tileWidth / 2 - g.getFontMetrics().stringWidth(coordinate) / 2;
            	int strY = (j - startY) * tileHeight + tileHeight / 2;
            	g.drawString(coordinate, strX, strY);

            	
            	
                
                //Draw enitty
                if (entities.getObjectAt(new CoordinatePair(i, j)) != null  ){
                    
                    
                	if(entities.getObjectAt(new CoordinatePair(i, j)).equals(avatar)) {
                            g.setColor(Color.blue);
                	} else {
                            g.setColor(Color.red);
                	}
                    Image img = avatarIcon.getImage();
                    g.drawImage(img, (i-startX)*64, (j-startY)*64, 64, 64, null);
                    //g.fillRect((i-startX)*64, (j-startY)*64, 63, 63);
                }
                
                if(projectiles != null){
                    for(Projectile p : projectiles){
                        int tileX = (int) p.getLocation().getX();
                        int tileY = (int) p.getLocation().getY();

                        g.fillOval(tileX - startX, tileY-startY, 64, 64);
                        }

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
        System.out.println(entities);

        entities = (Locations) mapObjects[2];
                entities.addObject(avatar);


        items = (Locations) mapObjects[3];
        traps = (Locations) mapObjects[4];
        projectiles = (ArrayList<Projectile>) mapObjects[5];

    }
}
