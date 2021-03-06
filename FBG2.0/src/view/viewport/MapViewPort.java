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
import model.map.areaEffect.AreaEffect;
import model.map.projectiles.Projectile;
import model.map.pair.CoordinatePair;
import model.map.tile.Tile;
import model.map.tile.trap.Trap;

/**
 *
 * @author ChrisMoscoso
 */
public class MapViewPort implements ViewPort, Observer {

    Tile[][] tiles;
    List<Tile> tilesAvatarCanSee;
    CoordinatePair avatarLocation;
    List<Entity> entitiesAvatarCanSee;
    List<Item> itemsAvatarCanSee;
    List<Trap> trapsAvatarCanSee;
    List<Projectile> projectilesAvatarCanSee;
    List<AreaEffect> areaEffectsAvatarCanSee;

    int widthInTiles = 0, heightInTiles = 0;
    BufferedImage currentTileImg, currentEntityImg;
    private int tileWidth = 64;
    private int tileHeight = 64;

    ImageIcon avatarIcon;
    BufferedImage fireballIcon;

    public MapViewPort() {

    }

    @Override
    public void draw(Graphics g) {
        //Calculate which portion of the map to draw based on avatar position.
        int windowWidth = (int) (GameDirector.getSize().width * 0.8);
        int windowHeight = (int) (GameDirector.getSize().height * 0.8);
        int windowWidthInTiles = windowWidth / tileWidth;
        int windowHeightInTiles = windowHeight / tileHeight;

        int startX = AvatarInteractionManager.getInstance().getAvatar().getLocation().getX() - windowWidthInTiles / 2;
        int startY = AvatarInteractionManager.getInstance().getAvatar().getLocation().getY() - windowHeightInTiles / 2;
        /*TO DO : SWITCH TO PUSH MODEL, SOMEWHERE YOU NEED AVATAR TO CALL UPDATE VIEW()*/
        //int startX = avatarLocation.getX() - windowWidthInTiles / 2;
        //int startY = avatarLocation.getY() - windowHeightInTiles / 2;

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
        String currentTileID;
        for (int i = startX; i < Math.min(startX + windowWidthInTiles, widthInTiles); i++) {
            for (int j = startY; j < Math.min(startY + windowHeightInTiles, heightInTiles); j++) {
                //Draw tiles
                // currentTileImg = SpriteFactory.getFog();
                g.setColor(Color.black);
                g.fillRect((i - startX) * tileWidth, (j - startY) * tileHeight, tileWidth, tileHeight);
                try {
                    for (Tile t : tilesAvatarCanSee) {

                        if (t.getLocation().equals(new CoordinatePair(i, j))) {
                            currentTileImg = SpriteFactory.hashIDtoImage(t.getID());
                            g.drawImage(currentTileImg, (i - startX) * tileWidth, (j - startY) * tileHeight, tileWidth, tileHeight, null);
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
            for (Trap t : trapsAvatarCanSee) {
                int tx = t.getLocation().getX();
                int ty = t.getLocation().getY();

                g.drawImage(SpriteFactory.hashIDtoImage(t.getID()), (tx - startX) * tileWidth, (ty - startY) * tileHeight, tileWidth, tileHeight, null);

            }

        } catch (ConcurrentModificationException e) {
        } catch (NoSuchElementException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
        }

        try {
            for (Projectile p : projectilesAvatarCanSee) {
                double px = (p.getLocation().getX());
                double py = (p.getLocation().getY());

                g.drawImage(SpriteFactory.hashIDtoImage(p.toString()), (int) ((px - startX) * tileWidth), (int) ((py - startY) * tileHeight), tileWidth, tileHeight, null);
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

                g.drawImage(SpriteFactory.hashIDtoImage(item.getID()), (int) ((px - startX) * tileWidth), (int) ((py - startY) * tileHeight), tileWidth, tileHeight, null);
            }

        } catch (NullPointerException e) {
        } catch (ConcurrentModificationException e) {
        } catch (NoSuchElementException e) {
            System.out.println(e);

        }

        try {
            for (AreaEffect aoe : areaEffectsAvatarCanSee) {
                double aoex = (aoe.getLocation().getX());
                double aoey = (aoe.getLocation().getY());

                g.drawImage(SpriteFactory.hashIDtoImage(aoe.getID()), (int) ((aoex - startX) * tileWidth), (int) ((aoey - startY) * tileHeight), tileWidth, tileHeight, null);
            }

        } catch (NullPointerException e) {
        } catch (ConcurrentModificationException e) {
        } catch (NoSuchElementException e) {
            System.out.println(e);

        }

        try {
            for (Entity e : entitiesAvatarCanSee) {
                Entity avatar = AvatarInteractionManager.getInstance().getAvatar();
                if (e.equals(avatar)) {
                    currentEntityImg = SpriteFactory.getAvatar(avatar.getDirection(), avatar.getOccupation());

                } else {
                    //Draw Entity Health Bars for all entities - avatar
                    CoordinatePair c = e.getLocation();
                    double percentageOfHealth = Math.min((double) e.getCurrentHP() / (double) e.getMaxHP(), 1);
                    g.setColor(Color.gray);
                    g.fillRoundRect((c.getX() - startX) * tileWidth, (c.getY() - startY) * tileHeight, tileWidth, 3, 5, 5);
                    g.setColor(Color.green);
                    g.fillRoundRect((c.getX() - startX) * tileWidth, (c.getY() - startY) * tileHeight, (int) (tileWidth * percentageOfHealth), 3, 5, 5);
                    currentEntityImg = SpriteFactory.hashIDtoImage(e.getID());
                }
                g.drawImage(currentEntityImg, (e.getLocation().getX() - startX) * tileWidth, (e.getLocation().getY() - startY) * tileHeight, tileWidth, tileHeight, null);
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
        areaEffectsAvatarCanSee = (List<AreaEffect>) mapObjects[7];
    }
}
