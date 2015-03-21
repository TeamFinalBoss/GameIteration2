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
    
    /**
     * uses the abilityToUseth ability
     * @param abilityTouse 
     */
    public void useAbility(int abilityTouse){
        avatar.useAbility(abilityTouse);        
    }
    
    /**
     * Uses an item from the sack
     * @author Jason Owens
     * @param slotNumber 
     */
    public void useItemAtSackSlot(int slotNumber){
        avatar.use(slotNumber);
    }
    
    /**
     * Uses an item from the sack
     * @author Jason Owens
     * @param slotNumber 
     */
    public void unequipAtSlot(int slotNumber){
        avatar.drop(slotNumber);
    }
    
    /**
     * TODO make this push to view
     * 
     * @author Jason Owens
     */
    public void update(){
        //pushToView(avatar.getSackContents(), currentSlotInArmory, currentSlotInSack);
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
    * Returns the number of items in the sack
    * @return sack size
    * @author Jason Owens
    */
    public int getSackSize(){
        return avatar.getSackSize();
    }
    
    public Entity getAvatar(){
    	return avatar;
    }
    
    
    
}
