package model.link;

import model.gameObject.MapObject;

/**
 * An abstract class that controls interactions between linked MapObjects
 * @author Aidan
 *
 */
public abstract class Link {
	private int linkID;
	private MapObject owner;
	
	public int getLink() { return linkID; }
	protected MapObject getOwner() { return owner; }
	
	public Link(MapObject owner, int linkID) {
		this.owner = owner;
		this.linkID = linkID;
	}
	
	/**
	 * Adds this link to the LinkList
	 * @see LinkList
	 */
	public void register() {
		LinkList.getInstance().addLink(getLink(), this);
	}
	
	/**
	 * Removes this link from the LinkList
	 * @see LinkList
	 */
	public void deregister() {
		LinkList.getInstance().removeLink(getLink(), this);
	}
}
