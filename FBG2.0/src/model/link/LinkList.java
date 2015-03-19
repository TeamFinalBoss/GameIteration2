package model.link;

import java.util.ArrayList;
import java.util.List;


/**
 * This singleton maintains a two dimensional list structure. 
 * This structure contains sets of links between different MapObjects,
 * as well as methods to modify links in the structure.
 * @author Aidan
 *
 */
public class LinkList {
	private static LinkList me;
	private ArrayList<LinkNumPair> links;
	
	public LinkList(){
        links = new ArrayList<LinkNumPair>();
    }
    
    public static LinkList getInstance(){
        if(me == null){
            me = new LinkList();
        }
        return me;
    }
    
    /**
     * Given a link, returns another link in the same set. It is assumed that
     * the returned link is the only other link in the set.
     * @param caller The link requesting its partner
     * @return Another link in the same set as the caller.
     */
    public Link getPair(Link caller) {
    	for(LinkNumPair p : links) {
    		boolean found = false;
    		Link pair = null;
    		
    		for(Link l : p.list) {
    			if(caller == l) found = true;
    			else pair = l;
    		}
    		
    		if(found && pair != null) return pair;
    	}
    	
    	return null;
    }
    
    /**
     * Given a link, returns all other links in the same set.
     * @param caller The link requesting a list of its partners
     * @return The list of all of the caller's partners
     */
    public List<Link> getPairs(Link caller) {
    	for(LinkNumPair p : links) {
    		boolean found = false;
    		List<Link> pairs = new ArrayList<Link>();
    		
    		for(Link l : p.list) {
    			if(caller == l) found = true;
    			else pairs.add(l);
    		}
    		
    		if(found && pairs.size() > 0) return pairs;
    	}
    	
    	return null;
    }
    
    /**
     * Adds a link to a set of links.
     * @param num The number corresponding to the link set
     * @param link The link to be added the set
     */
    public void addLink(int num, Link link) {
    	for(LinkNumPair p : links) {
    		if(p.num == num) {
    			p.list.add(link);
    			return;
    		}
    	}
    	
    	LinkNumPair lnp = new LinkNumPair(num);
    	lnp.list.add(link);
    	links.add(lnp);
    	return;
    }
    
    /**
     * Removes a link from a set of links.
     * @param num The number corresponding to the link set
     * @param link The link to be removed from the set
     */
    public void removeLink(int num, Link link) {
    	for(LinkNumPair p : links) {
    		if(p.num == num) {
    			p.list.remove(link);
    		}
    	}
    }
    
    private class LinkNumPair {
    	public int num;
    	public ArrayList<Link> list;
    	
    	public LinkNumPair(int num) {
    		this.num = num;
    		list = new ArrayList<Link>();
    	}
    }
}
