package model.link;

import java.util.ArrayList;
import java.util.List;

import model.director.ActiveMapManager;
import model.item.Item;

/**
 * A specific link that controls interactions between obstacles and obstacle clearers.
 * @author Aidan
 *
 */
public class ObstacleLink extends Link{
	public ObstacleLink(Item i, int linkID) { super(i, linkID); }
	
	private List<ObstacleLink> getPartners() {
		List<Link> l = LinkList.getInstance().getPairs(this);
		List<ObstacleLink> o = new ArrayList<ObstacleLink>();
		
		for(Link g : l) {
			o.add((ObstacleLink) g);
		}
		
		return o;
	}
	
	/**
	 * The link will request a list of all of its linked obstacles,
	 * then tell each link to remove its owner from the map.
	 */
	public void killObstacles() {
		List<ObstacleLink> partners = getPartners();
		if(partners == null) return;
		
		for(ObstacleLink p : partners) {
			p.killSelf();
		}
		
		return;
	}
	
	/**
	 * The link removes its owner from the active map.
	 */
	public void killSelf() {
		ActiveMapManager.getInstance().removeItemFromActiveMap((Item) getOwner());
		return;
	}
}
