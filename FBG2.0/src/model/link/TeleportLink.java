package model.link;

import model.director.ActiveMapManager;
import model.entity.Entity;
import model.map.areaEffect.TeleportAreaEffect;

/**
 * A specific link that controls interactions between entities.
 * @author Aidan
 *
 */
public class TeleportLink extends Link{
	public TeleportLink(TeleportAreaEffect ent, int linkID) { super(ent, linkID); }
	
	private TeleportLink getPartner() { return (TeleportLink) LinkList.getInstance().getPair(this); }
	
	/**
	 * Checks if an entity has a linked partner.
	 * @return whether or not the entity has a linked partner
	 */
	public void teleportToPartner(Entity e) {
		TeleportLink partner = getPartner();
		if(partner == null) return;
		
		partner.acceptTeleport(e);
	}
	
	public void acceptTeleport(Entity e) {
		if(((TeleportAreaEffect) getOwner()).getMap() == null) return;
		
		if(((TeleportAreaEffect) getOwner()).getMap() == ActiveMapManager.getInstance().getActiveMap()) {
			e.setLocation(getOwner().getLocation());
		}
		else
		{
			ActiveMapManager.getInstance().removeEntityFromActiveMap(e);
			ActiveMapManager.getInstance().setActiveMap(((TeleportAreaEffect) getOwner()).getMap());
			ActiveMapManager.getInstance().addEntityToActiveMap(e, getOwner().getLocation());
			ActiveMapManager.getInstance().setAvatar(e);
		}
		return;
	}
	
}
