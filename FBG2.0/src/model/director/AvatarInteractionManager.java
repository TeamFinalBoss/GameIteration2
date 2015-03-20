package model.director;

import model.entity.Entity;
import model.map.pair.CoordinatePair;
import model.map.Direction;
import model.entity.SummonerAvatar;

/**
 * The purpose of this class is to handle commands, events, or general information
 * that is specific to the avatar, and should thus not encumber the Active Map Manager
 * or other Entities.
 * @author Jason Owens
 */
public class AvatarInteractionManager {
    private Entity avatar;
    private ActiveMapManager AMM;
    private static AvatarInteractionManager me = null;
    private int currentSlotInSack;
    private int currentSlotInArmory;
    
 
    public AvatarInteractionManager(){
       AMM = ActiveMapManager.getInstance();
    }
    public AvatarInteractionManager(Entity avatar){
        this.avatar = avatar;
    }
    
    public static AvatarInteractionManager getInstance(){
    	if(me == null){
    		me = new AvatarInteractionManager();
    		me.avatar = new SummonerAvatar("Bob", "My default avatar description", new CoordinatePair(10,10));
    	}
    	return me;
    }
    
    public int getCurrentSlotInSack(){
        return currentSlotInSack;
    }
    
    public int getCurrentSlotInArmory(){
        return currentSlotInArmory;
    }
    
    
    /**
     * Moves the avatar
     * @param dir the direction to move
     * @return whether or not the movement is valid
     */
    public boolean moveAvatar(Direction dir){
        return AMM.requestMovement(avatar, dir);
    }
    
    /**
    * Should return the number of slots used in the sack including blank ones, if I understand
    * ArrayLists correctly. So, if you have 10 items and you remove the 5th one, it will still
    * return 10, but then if you add a new item it will still be 10 because the new item is 
    * going into the old, now empty slot.
    * @author Jason Owens
    */
    public int getSackSize(){
        return avatar.getSackSize();
    }
    
    public Entity getAvatar(){
    	return avatar;
    }
    
    
    
}
