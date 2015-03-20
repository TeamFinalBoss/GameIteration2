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
    
 
    public AvatarInteractionManager(){
       AMM = ActiveMapManager.getInstance();
    }
    public AvatarInteractionManager(Avatar avatar){
        this.avatar = avatar;
    }
    
    public boolean moveAvatar(Direction dir){
        return AMM.requestMovement(avatar, dir);
    }
    
    public int getSackSize(){
        avatar.getSackSize();
    }
    
    
    
}
