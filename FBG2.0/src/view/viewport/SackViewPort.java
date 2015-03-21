package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import model.director.GameDirector;
import model.factory.SpriteFactory;
import model.gameObject.entity.inventory.Sack;
import model.item.Takeable;
import utility.Bounds;

/**
 * The SackViewPort draws the sack of the avatar.
 *
 * @author ChrisMoscoso
 */
public class SackViewPort implements ViewPort, Observer {

    private int width, height, tileWidth = 64, tileHeight = 64;
    private Sack sack;
    private final ArrayList<Bounds> sackBounds;

    public SackViewPort() {
        sackBounds = new ArrayList<>();
    }

    @Override
    public void draw(Graphics g) {
        width = GameDirector.getSize().width;
        height = GameDirector.getSize().height;

        if (sack != null && sack.isVisible()) {

            sackBounds.clear();
            /*DRAW BOUNDS*/
            for (int i = sack.capacity() - 1; i >= 0; i--) {
                int x = width - tileWidth * (sack.capacity() - i);
                int y = height - tileHeight - 200;
                g.setColor(new Color(135, 206, 250));
                g.fillRect(x, y, tileWidth - 1, tileHeight);
                //Draw border
                if (sack.getCurrentSelection() == i) {
                    g.setColor(Color.red);//Highlight selection
                } else {
                    g.setColor(Color.blue);
                }

                g.drawRect(x, y, tileWidth - 1, tileHeight);  
                         sackBounds.add(new Bounds(x, y, tileWidth, tileHeight));
            }
            //Reverse the sack bounds since i drew them from right to left.
            Collections.reverse(sackBounds);

            /*DRAW ITEMS*/
            for (int i = 0; i < sack.contents().size(); i++) {
                Takeable t = sack.contents().get(i);
                BufferedImage img = SpriteFactory.getGenericSprite();
                Bounds b = sackBounds.get(i);
                g.drawImage(img, b.x, b.y, null);
            }
        }

        /*g.setColor(Color.BLUE);
         g.drawRect(0, height - tileHeight, tileWidth, tileHeight);
         g.drawRect(32, height - tileHeight, tileWidth, tileHeight);
         g.drawRect(64, height - tileHeight, tileWidth, tileHeight);
         g.drawRect(96, height - tileHeight, tileWidth, tileHeight);
         g.drawRect(128, height - tileHeight, tileWidth, tileHeight);*/
    }

    /**
     * Gets the bounds for each slot of the sack.
     *
     * @return the list sack bounds for the sack
     */
    public ArrayList<Bounds> getSackBounds() {
        return sackBounds;
    }

    @Override
    public void update(Observable o, Object arg) {
        Sack s = (Sack) arg;
        sack = s;
    }
}
