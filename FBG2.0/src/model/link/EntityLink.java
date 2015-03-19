package model.link;

import model.director.ActiveMapManager;
import model.entity.Entity;

/**
 * A specific link that controls interactions between entities.
 * @author Aidan
 *
 */
public class EntityLink extends Link{
	public EntityLink(Entity ent, int linkID) { super(ent, linkID); }
	
	private EntityLink getPartner() { return (EntityLink) LinkList.getInstance().getPair(this); }
	
	/**
	 * Checks if an entity has a linked partner.
	 * @return whether or not the entity has a linked partner
	 */
	public void killPartner() {
		EntityLink partner = getPartner();
		if(partner == null) return;
		
		partner.killSelf();
	}
	
	public void killSelf() {
		ActiveMapManager.getInstance().removeEntityFromActiveMap((Entity) getOwner());
		return;
	}
	
}
