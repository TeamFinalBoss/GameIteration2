package model.director;

import model.entity.Avatar;
import model.map.Direction;

/**
 * The purpose of this class is to handle commands, events, or general information
 * that is specific to the avatar, and should thus not encumber the Active Map Manager
 * or other Entities.
 * @author Jason Owens
 */
public class AvatarInteractionManager {
    private Avatar avatar;
    private ActiveMapManager AMM;
    
    private int currentSlotInSack;
    private int currentSlotInArmory;
    
 
    public AvatarInteractionManager(){
       AMM = ActiveMapManager.getInstance();
    }
    public AvatarInteractionManager(Avatar avatar){
        this.avatar = avatar;
    }
    
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
        pushToView(avatar.getSackContents(), currentSlotInArmory, currentSlotInSack);
    }
    
    public int getCurrentSlotInSack(){
        return currentSlotInSack;
    }
    
    public int getCurrentSlotInArmory(){
        return currentSlotInArmory;
    }
    
    public void useAbility(){
        avatar.
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
    
    
    
}
