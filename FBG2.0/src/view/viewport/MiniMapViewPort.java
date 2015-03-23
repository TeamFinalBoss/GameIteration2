package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import model.director.AvatarInteractionManager;
import model.director.GameDirector;
import model.entity.Entity;
import model.factories.SpriteFactory;
import model.item.Item;
import model.map.projectiles.Projectile;
import model.map.pair.CoordinatePair;
import model.map.tile.Tile;
import model.map.tile.trap.Trap;

/**
 *
 * @author ChrisMoscoso
 */
public class MiniMapViewPort implements ViewPort, Observer {

    Tile[][] tiles;
    List<Tile> tilesAvatarCanSee;
    CoordinatePair avatarLocation;
    List<Entity> entitiesAvatarCanSee;
    List<Item> itemsAvatarCanSee;
    List<Trap> trapsAvatarCanSee;
    List<Projectile> projectilesAvatarCanSee;

    int widthInTiles = 0, heightInTiles = 0;
    double scale = 0.08;
    BufferedImage currentTileImg, currentEntityImg;
    private int tileWidth = (int) (64 * scale);
    private int tileHeight = (int) (64 * scale);
    private int offsetX = (int) (GameDirector.getSize().width - GameDirector.getSize().width * 0.195);
    private int offsetY = (int) (GameDirector.getSize().height * 0.20);
    BufferedImage fireballIcon;

    @Override
    public void draw(Graphics g) {
        //Calculate which portion of the map to draw based on avatar position.
        int windowWidth = (int) (GameDirector.getSize().width * scale);
        int windowHeight = (int) (GameDirector.getSize().height * scale);
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

        for (int i = 0; i < widthInTiles; i++) {
            for (int j = 0; j < heightInTiles; j++) {
                //Draw tiles
                //currentTileImg = SpriteFactory.getFog();
                g.setColor(Color.black);
                g.fillRect((i) * tileWidth + offsetX, (j) * tileHeight + offsetY, tileWidth, tileHeight);

                try {
                    for (Tile t : tilesAvatarCanSee) {

                        if (t.getLocation().equals(new CoordinatePair(i, j))) {
                            currentTileImg = SpriteFactory.hashIDtoImage(t.getID());
                            g.drawImage(currentTileImg, (i) * tileWidth + offsetX, (j) * tileHeight + offsetY, tileWidth, tileHeight, null);
                            break;
                        }
                    }

                } catch (ConcurrentModificationException e) {
                } catch (NoSuchElementException e) {
                    System.out.println(e);
                } catch (NullPointerException e) {
                }

            }

        }

        try {
            for (Entity e : entitiesAvatarCanSee) {
                Entity avatar = AvatarInteractionManager.getInstance().getAvatar();
                if (e.equals(avatar)) {
                    currentEntityImg = SpriteFactory.getAvatar(avatar.getDirection());

                } else {
                    currentEntityImg = SpriteFactory.getGenericEntity(e.getDirection());
                }
                g.drawImage(currentEntityImg, (e.getLocation().getX() + offsetX) * tileWidth, (e.getLocation().getY() + offsetX) * tileHeight, tileWidth, tileHeight, null);
            }
        } catch (ConcurrentModificationException e) {
        } catch (NoSuchElementException e) {
            System.out.println(e);

        }

        try {
            for (Projectile p : projectilesAvatarCanSee) {
                double px = (p.getLocation().getX());
                double py = (p.getLocation().getY());

                g.drawImage(SpriteFactory.getFireball(), (int) (px * tileWidth) + offsetX, (int) (py * tileHeight) + offsetY, tileWidth, tileHeight, null);
            }

        } catch (NullPointerException e) {
        } catch (ConcurrentModificationException e) {
        } catch (NoSuchElementException e) {
            System.out.println(e);

        }

        try {
            for (Item item : itemsAvatarCanSee) {
                double px = (item.getLocation().getX());
                double py = (item.getLocation().getY());

                g.drawImage(SpriteFactory.getGenericObject(), (int) (px * tileWidth) + offsetX, (int) (py * tileHeight) + offsetY, tileWidth, tileHeight, null);
            }

        } catch (NullPointerException e) {
        } catch (ConcurrentModificationException e) {
        } catch (NoSuchElementException e) {
            System.out.println(e);

        }

        //Draw bounding box
        g.setColor(Color.red);
        g.drawRect(startX * tileWidth + offsetX, startY * tileWidth + offsetY, windowWidth, windowHeight);

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
}
