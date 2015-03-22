package model.director;

import model.entity.Entity;
import model.entity.SummonerAvatar;
import model.entity.SmasherAvatar;
import model.entity.SneakAvatar;
import model.map.pair.CoordinatePair;
import model.map.Direction;
import model.entity.SummonerAvatar;
import model.item.EquipSlot;

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
    private int statPoints;
    private int skillPoints;
    
 
    public AvatarInteractionManager(){
       AMM = ActiveMapManager.getInstance();
       statPoints = 0;
       skillPoints = 0;
       currentSlotInSack = 0;
       currentSlotInArmory = 0;
    }
    public AvatarInteractionManager(Entity avatar){
        this.avatar = avatar;
        AMM = ActiveMapManager.getInstance();
        statPoints = 0;
        skillPoints = 0;
        currentSlotInSack = 0;
        currentSlotInArmory = 0;
    }
    
    public static AvatarInteractionManager getInstance(){
    	if(me == null){
    		me = new AvatarInteractionManager();
    		me.avatar = new SummonerAvatar();
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
    public void unequipAtSlot(EquipSlot slot){
        avatar.unequip(slot);
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
    
	public void dropItemAtSlot(int currentIndex) {
		avatar.drop(currentIndex);
	}
    public void setAvatar(Entity next){
    	avatar = next;
    }
    public int getSkillPoints(){
    	return skillPoints;
    }
    public int getStatPoints(){
    	return statPoints;
    }
    public void modifySkillPoints(int modifier){
    	skillPoints += modifier;
    }
    public void modifyStatPoints(int modifier){
    	statPoints += modifier;
    }
	public void increaseStrength(){
		if(statPoints <= 0) return;
		avatar.modifyStrength(1);
		--statPoints;
	}
	public void increaseAgility(){
		avatar.modifyAgility(1);
		--statPoints;
	}
	public void increaseIntellect(){
		if(statPoints <= 0) return;
		avatar.modifyIntellect(1);
		--statPoints;
	}
	public void increaseHardiness(){
		if(statPoints <= 0) return;
		avatar.modifyHardiness(1);
		--statPoints;
	}
	public void increaseMovement(){
		if(statPoints <= 0) return;
		avatar.modifyMovement(1);
		--statPoints;
	}
	public void increaseBindWounds(){
		if(skillPoints <= 0) return;
		avatar.modifyBindWounds(1);
		--skillPoints;
	}
	public void increaseBargain(){
		if(skillPoints <= 0) return;
		avatar.modifyBargain(1);
		--skillPoints;
	}
	public void increaseObservation(){
		if(skillPoints <= 0) return;
		avatar.modifyObservation(1);
		--skillPoints;
	}
	public void increaseClassSkill1(){
		if(skillPoints <= 0) return;
		if(avatar.getOccupation().equals("summoner")){
			((SummonerAvatar) avatar).modifyEnchantment(1);
		}
		if(avatar.getOccupation().equals("smasher")){
			((SmasherAvatar) avatar).modifyOneHanded(1);
		}
		if(avatar.getOccupation().equals("sneak")){
			((SneakAvatar) avatar).modifyPickPocket(1);
		}
		--skillPoints;
	}
	public void increaseClassSkill2(){
		if(skillPoints <= 0) return;
		if(avatar.getOccupation().equals("summoner")){
			((SummonerAvatar) avatar).modifyBane(1);
		}
		if(avatar.getOccupation().equals("smasher")){
			((SmasherAvatar) avatar).modifyTwoHanded(1);
		}
		if(avatar.getOccupation().equals("sneak")){
			((SneakAvatar) avatar).modifyTrapSkill(1);
		}
		--skillPoints;
	}
	public void increaseClassSkill3(){
		if(skillPoints <= 0) return;
		if(avatar.getOccupation().equals("summoner")){
			((SummonerAvatar) avatar).modifyBoon(1);
		}
		if(avatar.getOccupation().equals("smasher")){
			((SmasherAvatar) avatar).modifyBrawling(1);
		}
		if(avatar.getOccupation().equals("sneak")){
			((SneakAvatar) avatar).modifyCreep(1);
		}
		--skillPoints;
	}
	public void increaseClassSkill4(){
		if(skillPoints <= 0) return;
		if(avatar.getOccupation().equals("summoner")){
			((SummonerAvatar) avatar).modifyStaff(1);
		}
		if(avatar.getOccupation().equals("smasher")){
			((SmasherAvatar) avatar).modifyChakra(1);
		}
		if(avatar.getOccupation().equals("sneak")){
			((SneakAvatar) avatar).modifyRangedWeapon(1);
		}
		--skillPoints;
	}
}
