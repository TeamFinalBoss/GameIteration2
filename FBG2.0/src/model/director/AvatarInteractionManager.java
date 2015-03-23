package model.director;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Random;

import controller.menu.stats.StatsMenu;
import controller.sceneControllers.SceneType;
import java.io.FileInputStream;
import java.io.InputStream;
import model.map.areaEffect.AreaEffect;
import model.map.pair.PurePair;
import model.effect.Dispellable;
import model.entity.Entity;
import model.entity.MotionType;
import model.entity.NPC;
import model.entity.SummonerAvatar;
import model.entity.SmasherAvatar;
import model.entity.SneakAvatar;
import model.item.Equipable;
import model.item.Item;
import model.item.Takeable;
import model.map.pair.CoordinatePair;
import model.map.tile.Tile;
import model.map.tile.trap.Trap;
import model.map.Direction;
import model.map.projectiles.Projectile;
import model.entity.SummonerAvatar;
import model.entity.ability.Ability;
import model.item.EquipSlot;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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
    private NPC conversationPartner;
    
    //Vector avatarDirection;
    
    private Map<SceneType, Observer> observers;
 
    public AvatarInteractionManager(){
       AMM = ActiveMapManager.getInstance();
       statPoints = 10;
       skillPoints = 10;
       currentSlotInSack = 0;
       currentSlotInArmory = 0;
       me = this;
       observers = new HashMap<>();
    }
    public AvatarInteractionManager(Entity avatar){
        this.avatar = avatar;
        AMM = ActiveMapManager.getInstance();
        statPoints = 10;
        skillPoints = 10;
        currentSlotInSack = 0;
        currentSlotInArmory = 0;
        me = this;
        observers = new HashMap<>();
    }
    
    public static AvatarInteractionManager getInstance(){
    	if(me == null){
    		me = new AvatarInteractionManager();
    		me.avatar = new SummonerAvatar();
    	}
    	return me;
    }
    
    public void addObserver(SceneType type, Observer o) {
    	observers.put(type,o);
    }
    
    /**
     * uses the abilityToUseth ability
     * @param abilityTouse 
     */
    public void useAbility(int abilityTouse){
        try {
            InputStream in = new FileInputStream("./src/resources/sound/pewpew.wav");
            AudioStream as = new AudioStream(in);

            AudioPlayer.player.start(as);
        } catch (Exception e) {
            System.out.println(e);
        }
        avatar.useAbility(abilityTouse);
        observers.get(SceneType.STATS_UPDATING).update(null, null);
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
    
    public Entity getAvatar(){
    	return avatar;
    }
    
	public void dropItemAtSlot(int currentIndex) {
		avatar.drop(currentIndex);
	}
    public void setAvatar(Entity next){
    	avatar = next;
    }
    
    /* --------------------  SKILL ASSIGNMENT SYSTEM -------------------- */
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
	
	/* -------------------- AVATAR ACCESSORS -------------------- */
	public List<Takeable> getSack(){
		return avatar.getSackContents();
	}
	public int getSackSize(){
		return avatar.getSackSize();
	}
	public int getSackCapacity(){
		return avatar.getSackCapacity();
	}
	public Map<EquipSlot,Equipable> getArmoryContents(){
		return avatar.getArmoryContents();
	}
	public int getLivesLeft(){
		return avatar.getLivesLeft();
	}
	public int getStrength(){
		return avatar.getStrength();
	}
	public int getAgility(){
		return avatar.getAgility();
	}
	public int getIntellect(){
		return avatar.getIntellect();
	}
	public int getHardiness(){
		return avatar.getHardiness();
	}
	public int getExperience(){
		return avatar.getExperience();
	}
	public int getMovement(){
		return avatar.getMovement();
	}
	public int getLevel(){
		return avatar.getLevel();
	}
	public int getMaxHP(){
		return avatar.getMaxHP();
	}
	public int getMaxMP(){
		return avatar.getMaxMP();
	}
	public int getOffense(){
		return avatar.getOffense();
	}
	public int getDefense(){
		return avatar.getDefense();
	}
	public int getArmor(){
		return avatar.getArmor();
	}
	public int getBindWounds(){
		return avatar.getBindWounds();
	}
	public int getBargain(){
		return avatar.getBargain();
	}
	public int getObservation(){
		return avatar.getObservation();
	}
	public int getCurrentHP(){
		return avatar.getCurrentHP();
	}
	public int getCurrentMP(){
		return avatar.getCurrentMP();
	}
	public int getWeaponOffense(){
		return avatar.getWeaponOffense();
	}
	public int getEquipArmor(){
		return avatar.getEquipArmor();
	}
	public PurePair<String,Integer> getClassSkill1(){
		if(avatar.getOccupation().equals("summoner")){
			return new PurePair<String,Integer>("Enchantment", new Integer(((SummonerAvatar) avatar).getEnchantment()));
		}
		if(avatar.getOccupation().equals("smasher")){
			return new PurePair<String,Integer>("One-Handed", new Integer(((SmasherAvatar) avatar).getOneHanded()));
		}
		if(avatar.getOccupation().equals("sneak")){
			return new PurePair<String,Integer>("Pickpocket", new Integer(((SneakAvatar) avatar).getPickPocket()));
		}
		return new PurePair<String,Integer>();
	}
	public PurePair<String,Integer> getClassSkill2(){
		if(avatar.getOccupation().equals("summoner")){
			return new PurePair<String,Integer>("Bane", new Integer(((SummonerAvatar) avatar).getBane()));
		}
		if(avatar.getOccupation().equals("smasher")){
			return new PurePair<String,Integer>("Two-Handed", new Integer(((SmasherAvatar) avatar).getTwoHanded()));
		}
		if(avatar.getOccupation().equals("sneak")){
			return new PurePair<String,Integer>("Trap", new Integer(((SneakAvatar) avatar).getTrapSkill()));
		}
		return new PurePair<String,Integer>();
	}
	public PurePair<String,Integer> getClassSkill3(){
		if(avatar.getOccupation().equals("summoner")){
			return new PurePair<String,Integer>("Boon", new Integer(((SummonerAvatar) avatar).getBoon()));
		}
		if(avatar.getOccupation().equals("smasher")){
			return new PurePair<String,Integer>("Brawling", new Integer(((SmasherAvatar) avatar).getBrawling()));
		}
		if(avatar.getOccupation().equals("sneak")){
			return new PurePair<String,Integer>("Creep", new Integer(((SneakAvatar) avatar).getCreep()));
		}
		return new PurePair<String,Integer>();
	}
	public PurePair<String,Integer> getClassSkill4(){
		if(avatar.getOccupation().equals("summoner")){
			return new PurePair<String,Integer>("Staff", new Integer(((SummonerAvatar) avatar).getStaff()));
		}
		if(avatar.getOccupation().equals("smasher")){
			return new PurePair<String,Integer>("Chakra", new Integer(((SmasherAvatar) avatar).getChakra()));
		}
		if(avatar.getOccupation().equals("sneak")){
			return new PurePair<String,Integer>("Ranged Weapon", new Integer(((SneakAvatar) avatar).getRangedWeapon()));
		}
		return new PurePair<String,Integer>();
	}
	public List<Ability> getAllAbilities(){
		return avatar.getAllAbilities();
	}
	public List<Tile> getVisibleTiles(){
		return avatar.getVisibleTiles();
	}
	public List<Projectile> getVisibleProjectiles(){
		return avatar.getVisibleProjectiles();
	}
	public List<Entity> getVisibleEntities(){
		return avatar.getVisibleEntities();
	}
	public List<Trap> getVisibleTraps(){
		return avatar.getVisibleTraps();
	}
	public List<Item> getVisibleItems(){
		return avatar.getVisibleItems();
	}
	public List<AreaEffect> getVisibleAreaEffects(){
		return avatar.getVisibleAreaEffects();
	}
	public List<Dispellable> getEffects(){
		return avatar.getEffects();
	}
	public int getCurrency(){
		return avatar.getCurrency();
	}
	public Direction getDirection(){
		return avatar.getDirection();
	}
	public MotionType getMotionType(){
		return avatar.getMotionType();
	}
	public String getOccupation(){
		return avatar.getOccupation();
	}
	
	/* -------------------- DIALOGUE INTERACTION -------------------- */
	public void useDialogueOption(int selection){
		conversationPartner.traverseDialogue(selection);
	}
	public NPC getConversationPartner(){
		return conversationPartner;
	}
	public void setConversationPartner(Entity e){
		conversationPartner = (NPC) e;
	}
	
	/* ------------------- STORE INTERACTION -------------------- */
	public void buyItem(int position){
		avatar.insert(conversationPartner.sellItem(position, avatar));
	}
	public void sellItem(int position){
		conversationPartner.buyItem(avatar.remove(position),avatar);
	}
	public List<String> getObservationInformation(){
    	List<String> returnValue = new ArrayList<String>();
    	List<Entity> potentialEntities = avatar.getVisibleEntities();
    	Entity observing = null;
    	for(Entity potential : potentialEntities){
    		if(potential.getLocation().getX() == avatar.tileInFront().getX() && potential.getLocation().getY() == avatar.tileInFront().getY()){
    			observing = potential;
    			break;
    		}
    }
    	if(observing == null) returnValue.add("You see nothing.");
    	else{
    		returnValue.add("Name: " + observing.getName());
    		returnValue.add("Strength: " + observing.getStrength() * avatar.getConfoundingFactor());
    		returnValue.add("Agility: " + observing.getAgility() * avatar.getConfoundingFactor());
    		returnValue.add("Intellect: " + observing.getIntellect() * avatar.getConfoundingFactor());
    		returnValue.add("Hardiness: " + observing.getHardiness() * avatar.getConfoundingFactor());
    		returnValue.add("Movement: " + observing.getMovement() * avatar.getConfoundingFactor());
    	}
    	return returnValue;
    	
    }
}
