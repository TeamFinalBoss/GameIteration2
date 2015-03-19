package model.map;

/**
 * This singleton class executes movement commands for entities and activates any 
 * effects or consequences that come with moving onto a tile, such as picking
 * up an item.
 * 
 * @see ActiveMapManager
 * @author Michael Cohen
 *
 */
public class MotionCoordinator {
	private static MotionCoordinator me = null;
	
	private MotionCoordinator(){
		
	}
	
	public static MotionCoordinator getInstance(){
		if (me == null){
			me = new MotionCoordinator();
		}
		
		return me;
	}
	
	public void moveEntity(){
		
	}
}
