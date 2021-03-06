package model.factories;

import java.util.ArrayList;
import java.util.List;

import model.gameObject.MapObject;
import model.map.areaEffect.AreaEffect;
import model.map.areaEffect.InstantDeathAreaEffect;
import model.map.areaEffect.LevelUpAreaEffect;
import model.map.areaEffect.TakeDamageAreaEffect;
import model.map.areaEffect.TeleportAreaEffect;
import model.map.pair.CoordinatePair;
import model.map.tile.trap.Trap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class examines a parsed XML file for all one shot entries, then uses
 * their attributes to instantiate them.
 *
 * @author Aidan Pace
 */
public class AreaEffectFactory implements PlaceableObjectFactory {

    public AreaEffectFactory() {
    }

    /**
     * Examine all nodes that are children of the input node, filter out trap
     * elements, then use their attributes to instantiate them
     *
     * @author Aidan Pace
     * @param head the node to begin search at
     * @return the list of trap items created by this method
     * @see Trap
     */
    public List<MapObject> generate(Element head) {
        List<MapObject> items = new ArrayList<MapObject>();

        NodeList nodes = head.getElementsByTagName("areaeffect");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element item = (Element) nodes.item(i);
            AreaEffect it = null;

            switch (item.getAttribute("name")) {
                case "instantDeath":
                    it = new InstantDeathAreaEffect();
                    //it.setID(item.getAttribute("instantDeath"));
                    break;
                case "levelUp":
                    it = new LevelUpAreaEffect();
                    //it.setID(item.getAttribute("levelUp"));
                    break;
                case "takeDamage":
                    it = new TakeDamageAreaEffect();
                    //it.setID(item.getAttribute("name"));
                    break;
                case "teleport":
                    it = new TeleportAreaEffect();
                    //it.setID(item.getAttribute("name"));

                    ((TeleportAreaEffect) it).setLink(Integer.parseInt(item.getAttribute("link")));
                    break;
            }

            if (it == null) {
                continue;
            }

            it.setLocation(new CoordinatePair(Integer.parseInt(item.getAttribute("x")), Integer.parseInt(item.getAttribute("y"))));

            items.add(it);
        }

        return items;
    }
}
