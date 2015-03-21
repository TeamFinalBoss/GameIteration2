package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import model.gameObject.entity.inventory.Armory;

/**
 * The armory viewport draws the armory of the avatar.
 *
 * @author ChrisMoscoso
 */
public class ArmoryViewPort implements ViewPort, Observer {

    private Armory armory;
    private int width, height, tileWidth = 64, tileHeight = 64;

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, height - 200, 160, height);

        g.setColor(Color.WHITE);
        g.fillRect(0, height - tileHeight, tileWidth, tileHeight);
        g.fillRect(32, height - tileHeight, tileWidth, tileHeight);
        g.fillRect(64, height - tileHeight, tileWidth, tileHeight);
        g.fillRect(96, height - tileHeight, tileWidth, tileHeight);
        g.fillRect(128, height - tileHeight, tileWidth, tileHeight);

        
        try {
            BufferedImage head = ImageIO.read(getClass().getResource("/resources/singleSprites/head.jpg"));
            BufferedImage weapon = ImageIO.read(getClass().getResource("/resources/singleSprites/weapon.jpg"));
            BufferedImage torso = ImageIO.read(getClass().getResource("/resources/singleSprites/torso.jpg"));
            BufferedImage pants = ImageIO.read(getClass().getResource("/resources/singleSprites/pants.jpg"));
            BufferedImage boots = ImageIO.read(getClass().getResource("/resources/singleSprites/boots.jpg"));

            BufferedImage[] slots = {head, weapon, torso, pants, boots};
            
            
            /*Armory Items*/
            /*for (int i = 0; i < 5; i++) {
                Takeable t = InventoryCoordinator.getInstance().getItem(i);
                BufferedImage img;
                if (t != null) {
                    img = ImageSplitter.getInstance().getTileFromID(t.getID());
                } else {
                    img = slots[i];
                }
                g.setColor(Color.yellow);
                //Draw the slots in a straight line. Except for the weapon
                if (i == 0) {
                    if (InventoryCoordinator.getInstance().getSelectedItemIndex() == i) {
                        g.drawRect(64, height - 200 + (i * 35), tileWidth, tileHeight);
                    }
                    g.drawImage(img, 64, height - 200 + (i * 35), null);
                } else if (i == 1) {
                    if (InventoryCoordinator.getInstance().getSelectedItemIndex() == i) {
                        g.drawRect(32, height - 165, tileWidth, tileHeight);
                    }
                    g.drawImage(img, 32, height - 165, null);
                } else {
                    if (InventoryCoordinator.getInstance().getSelectedItemIndex() == i) {
                        g.drawRect(64, height - 200 + ((i - 1) * 35), tileWidth, tileHeight);
                    }
                    g.drawImage(img, 64, height - 200 + ((i - 1) * 35), null);
                }

            }

            g.setColor(Color.yellow);
            //Check at the end for selected item
            for (int i = 0; i < 10; i++) {
                if (i == 0) {
                    if (InventoryCoordinator.getInstance().getSelectedItemIndex() == i) {
                        g.drawRect(64, height - 200 + (i * 35), tileWidth, tileHeight);
                    }
                }
                if (i == 1) {
                    if (InventoryCoordinator.getInstance().getSelectedItemIndex() == i) {
                        g.drawRect(32, height - 165, tileWidth, tileHeight);
                    }
                }
                if (i > 1 && i < 5) {
                    if (InventoryCoordinator.getInstance().getSelectedItemIndex() == i) {
                        g.drawRect(64, height - 200 + ((i - 1) * 35), tileWidth, tileHeight);
                    }
                }
                if (i >= 5) {
                    if (InventoryCoordinator.getInstance().getSelectedItemIndex() == i) {
                        g.drawRect((i - 5) * tileWidth, height - tileHeight, tileWidth, tileHeight);
                    }
                }
            }*/

        }catch(Exception e){}
    }

    @Override
    public void update(Observable o, Object arg) {
        Armory a = (Armory) arg;
        armory = a;
    }

}
