package model.map.areaEffect;

import model.entity.Entity;
import model.link.TeleportLink;
import model.map.GameMap;

public class TeleportAreaEffect extends AreaEffect {
	
	private TeleportLink link;
	private GameMap map;
	
	public TeleportAreaEffect() {
		super();
		this.setName("teleport");
		this.link = new TeleportLink(this, 0);
		this.map = null;
	}
	
	public GameMap getMap() {
		return map;
	}
	
	public void setMap(GameMap m) {
		map = m;
	}
	
	public int getLink() {
		return link.getLink();
	}
	
	public void setLink(int newlink) {
		link = new TeleportLink(this, newlink);
	}

	@Override
	public void activate(Entity caller) {
		link.teleportToPartner(caller);
	}

}
