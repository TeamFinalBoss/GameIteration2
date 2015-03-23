package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.director.AvatarInteractionManager;
import model.director.GameDirector;
import model.entity.Entity;
import model.item.Item;
import model.map.Direction;
import model.map.Vector;
import model.map.projectiles.Projectile;
import model.map.pair.CoordinatePair;
import model.map.pair.PreciseCoordinatePair;
import model.map.pair.PurePair;
import model.map.tile.Tile;
import model.map.tile.trap.Trap;

/**
 *
 * @author ChrisMoscoso
 */
public class MapViewPort implements ViewPort, Observer, DirectionChanger {

    Tile[][] tiles;
    List<Tile> tilesAvatarCanSee;
    CoordinatePair avatarLocation;
    List<Entity> entitiesAvatarCanSee;
    List<Item> itemsAvatarCanSee;
    List<Trap> trapsAvatarCanSee;
    List<Projectile> projectilesAvatarCanSee;

    int widthInTiles = 0, heightInTiles = 0;
    BufferedImage grass;
    private int tileWidth = 64;
    private int tileHeight = 64;

    ImageIcon avatarIcon;
    BufferedImage fireballIcon;

    public MapViewPort() {
        try {
            grass = ImageIO.read(new File("src/resources/sprites/LightGrass.png"));
            //avatarImage = ImageIO.read(new File("src/resources/img/summonerUp.gif"));
            avatarIcon = new ImageIcon("src/resources/img/summonerUp.gif");
            File file = new File("src/resources/sprites/fireball.png");
            fireballIcon = ImageIO.read(file);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void draw(Graphics g) {
        //Calculate which portion of the map to draw based on avatar position.
        int windowWidth = (int) (GameDirector.getSize().width * 0.8);
        int windowHeight = (int) (GameDirector.getSize().height * 0.8);
        int windowWidthInTiles = windowWidth / tileWidth;
        int windowHeightInTiles = windowHeight / tileHeight;
        int startX = avatarLocation.getX() - windowWidthInTiles / 2;
        int startY = avatarLocation.getY() - windowHeightInTiles / 2;

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

        for (int i = startX; i < Math.min(startX + windowWidthInTiles, widthInTiles); i++) {
            for (int j = startY; j < Math.min(startY + windowHeightInTiles, heightInTiles); j++) {
                //Draw tile
                //TODO: Make it so it doesnt just draw grass       
                

                //Draw tiles
                g.setColor(Color.black);
                try {
                    for (Tile t : tilesAvatarCanSee) {
                        if (tiles[i][j].equals(t)) {
                            //System.out.println("We should see ")
                            g.setColor(Color.green);
                           // g.drawImage(grass, (i - startX) * tileWidth, (j - startY) * tileHeight, tileWidth, tileHeight, null);
                        }else{
                            
                        }
                    }
                    g.fillRect((i - startX) * tileWidth, (j - startY) * tileHeight, tileWidth, tileHeight);
                } catch (ConcurrentModificationException e) {
                } catch (NoSuchElementException e) {
                    System.out.println(e);
                }

                /*
                 //Draw enitty
                 if (entities.getObjectAt(new CoordinatePair(i, j)) != null) {

                 if (entities.getObjectAt(new CoordinatePair(i, j)).equals(avatar)) {
                 g.setColor(Color.blue);
                 Image img = avatarIcon.getImage();
                 g.drawImage(img, (i - startX) * 64, (j - startY) * 64, 64, 64, null);
                 } else {
                 g.setColor(Color.red);
                 g.fillRect((i - startX) * 64, (j - startY) * 64, 63, 63);
                 }

                 }

                 if (projectiles != null) {
                 try {

                 for (Projectile p : projectiles) {
                 double tileX = p.getLocation().getX();
                 double tileY = p.getLocation().getY();

                 g.fillOval((int) ((tileX - startX) * tileWidth), (int) ((tileY - startY) * tileWidth), tileWidth, tileWidth);
                 }

                 } catch (ConcurrentModificationException e) {

                 }

                 }*/
                //draw coordinates
                g.setColor(Color.BLUE);
                String coordinate = "(" + i + "," + j + ")";
                int strX = (i - startX) * tileWidth + tileWidth / 2 - g.getFontMetrics().stringWidth(coordinate) / 2;
                int strY = (j - startY) * tileHeight + tileHeight / 2;
                g.drawString(coordinate, strX, strY);

            }

        }

        try {
            for (Entity e : entitiesAvatarCanSee) {
                if (e.equals(AvatarInteractionManager.getInstance().getAvatar())) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.blue);
                }
                g.fillRect((e.getLocation().getX() - startX) * tileWidth, (e.getLocation().getY() - startY) * tileHeight, tileWidth, tileHeight);
            }
        } catch (ConcurrentModificationException e) {
        } catch (NoSuchElementException e) {
            System.out.println(e);

        }

        try {
            for (Projectile p : projectilesAvatarCanSee) {
                double px = (p.getLocation().getX());
                double py = (p.getLocation().getY());

                g.drawOval((int) ((px - startX) * tileWidth), (int) ((py - startY) * tileHeight), tileWidth, tileHeight);
            }

        } catch (ConcurrentModificationException e) {
        } catch (NoSuchElementException e) {
            System.out.println(e);

        }

    }

    @Override
    public void update(Observable o, Object arg) {
        Object[] mapObjects = (Object[]) arg;
        tiles = (Tile[][]) mapObjects[0];
        widthInTiles = tiles.length;
        heightInTiles = tiles[0].length;

        tilesAvatarCanSee = (List<Tile>) mapObjects[1];

        avatarLocation = (CoordinatePair) mapObjects[2];
        entitiesAvatarCanSee = (List<Entity>) mapObjects[3];
        itemsAvatarCanSee = (List<Item>) mapObjects[4];
        trapsAvatarCanSee = (List<Trap>) mapObjects[5];
        projectilesAvatarCanSee = (List<Projectile>) mapObjects[6];

    }
    
    public Direction changeDirection(Point p) {
    	int windowWidth = (int) (GameDirector.getSize().width * 0.8);
        int windowHeight = (int) (GameDirector.getSize().height * 0.8);
        int windowWidthInTiles = windowWidth / tileWidth;
        int windowHeightInTiles = windowHeight / tileHeight;
        int startX = avatarLocation.getX() - windowWidthInTiles / 2;
        int startY = avatarLocation.getY() - windowHeightInTiles / 2;

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
        
        
        int xLocation = avatarLocation.getX() - startX + tileWidth / 2;
        int yLocation = (avatarLocation.getY() - startY) * tileHeight + tileWidth / 2;
        
        Vector vector1 = new Vector();
        
        double value = p.getX() - xLocation;
        double value2 = p.getY() - yLocation;
        
        System.out.println(value);
        System.out.println(value2);
        
        
        vector1.set(value, value2);
        
        double angle = Math.atan2( vector1.getY(), vector1.getX() );
        int octant = (int) (Math.round( 8 * angle / (2*Math.PI) + 8 ) % 8);
        
        switch(octant) {
        	case 0 :
        		return Direction.East;
        	case 1 :
        		return Direction.NorthEast;	
        	case 2: 
        		return Direction.North;
        	case 3: 
        		return Direction.NorthWest;
        	case 4:
        		return Direction.West;
        	case 5 :
        		return Direction.SouthWest;
        	case 6 :
        		return Direction.South;
        	case 7 :
        		return Direction.SouthEast;
        	default :
        		return null;
        }
        
       
        
        
    }

}
