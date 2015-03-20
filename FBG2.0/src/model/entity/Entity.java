package model.entity;

import model.entity.ability.AbilityLibrary;
import model.entity.inventory.Inventory;
import model.entity.stats.Stats;
import model.gameObject.MapObject;
import model.map.Direction;
import model.map.pair.CoordinatePair;

import java.util.List;

import model.item.Takeable;
import model.item.Equipable;
import model.item.EquipSlot;

import java.util.Map;

import model.director.ActiveMapManager;

/** 
 * The class Entity defines a common type for all entities (beings) in the game. 
 * 
 * ID: 1
 * 
 * @see MapObject
 * @author Matthew Kroeze, Chris Moscoso, Michael Cohen
 * @version 1.1.0 2015-03-15
 */
public abstract class Entity extends MapObject{
    private Inventory myInventory;
    private AbilityLibrary myAbilities;
    private Stats myStats;
    private Direction myDirection;
    private ActiveMapManager activeMap; //forwards messages to the active map
    private int currency;
    private MotionType motionType;

    /* -------------------- PROTECTED COMPONENT CREATION -------------------- */
    protected Inventory createInventory(){
    	return new Inventory(10,this);
    }
    protected abstract AbilityLibrary createAbilities();
    protected abstract Stats createStats();
    
    /* -------------------- CONSTRUCTORS --------------------*/
    public Entity(String objectName, 
    			  String description, 
    			  CoordinatePair location){
    	super(objectName, description, location);
    	myInventory = createInventory();
    	myAbilities = createAbilities();
    	myStats = createStats();
    	myDirection = Direction.North;
    	activeMap = ActiveMapManager.getInstance();
    	currency = 0;
    	motionType = MotionType.GROUND;
    }
    
    /* -------------------- PRIVATE UTILITY -------------------- */
    private int max(int a, int b){
    	return a > b ? a : b;
    }
    
    /* -------------------- INVENTORY ACCESSORS --------------------*/
    public List<Takeable> getSackContents(){
    	return myInventory.sackContents();
    }
    public int getSackSize(){
    	return myInventory.sackSize();
    }
    public int getSackCapacity(){
    	return myInventory.sackCapacity();
    }
    public Map<EquipSlot,Equipable> getArmoryContents(){
    	return myInventory.armoryContents();
    }
    
    /* -------------------- INVENTORY MUTATORS ---------------------*/
    public boolean use(int position){
    	return myInventory.use(position);
    }
    public Takeable remove(int position){
    	return myInventory.remove(position);
    }
    public boolean insert(Takeable next){
    	return myInventory.insert(next);
    }
    public void equip(Equipable next){
    	myInventory.equip(next);
    }
    public Equipable unequip(EquipSlot slot){
    	return myInventory.unequip(slot);
    }
    public Takeable remove(Takeable item){
    	return myInventory.remove(item);
    }
    public void drop(int position){
    	activeMap.addItemToActiveMap(remove(position),getLocation());
    }
    
    /* -------------------- STATS ACCESSORS -------------------- */
    public int getLivesLeft(){
		return myStats.livesLeft();
	}
	public int getStrength(){
		return myStats.strength();
	}
	public int getAgility(){
		return myStats.agility();
	}
	public int getIntellect(){
		return myStats.intellect();
	}
	public int getHardiness(){
		return myStats.hardiness();
	}
	public int getExperience(){
		return myStats.experience();
	}
	public int getMovement(){
		return myStats.movement();
	}
	public int getLevel(){
		return myStats.level();
	}
	public int getMaxHP(){
		return myStats.maxHP();
	}
	public int getMaxMP(){
		return myStats.maxMP();
	}
	public int getOffense(){
		return myStats.offense();
	}
	public int getDefense(){
		return myStats.defense();
	}
	public int getArmor(){
		return myStats.armor();
	}
	public int getBindWounds(){
		return myStats.bindWounds();
	}
	public int getBargain(){
		return myStats.bargain();
	}
	public int getObservation(){
		return myStats.observation();
	}
	public int getCurrentHP(){
		return myStats.currentHP();
	}
	public int getCurrentMP(){
		return myStats.currentMP();
	}
	public int getWeaponOffense(){
		return myStats.weaponOffense();
	}
	public int getEquipArmor(){
		return myStats.equipArmor();
	}
	
	/* -------------------- STATS SET MUTATORS -------------------- */
	public void setLivesLeft(int next){
		myStats.setLivesLeft(next);
	}
	public void setStrength(int next){
		myStats.setStrength(next);
	}
	public void setAgility(int next){
		myStats.setAgility(next);
	}
	public void setIntellect(int next){
		myStats.setIntellect(next);
	}
	public void setHardiness(int next){
		myStats.setHardiness(next);
	}
	public void setExperience(int next){
		myStats.setExperience(next);
	}
	public void setMovement(int next){
		myStats.setMovement(next);
	}
	public void setBindWounds(int next){
		myStats.setBindWounds(next);
	}
	public void setBargain(int next){
		myStats.setBargain(next);
	}
	public void setObservation(int next){
		myStats.setObservation(next);
	}
	public void setCurrentHP(int next){
		myStats.setCurrentHP(next);
	}
	public void setCurrentMP(int next){
		myStats.setCurrentMP(next);
	}
	public void setWeaponOffense(int next){
		myStats.setWeaponOffense(next);
	}
	public void setEquipArmor(int next){
		myStats.setEquipArmor(next);
	}
    
	/* -------------------- STATS MODIFY MUTATORS -------------------- */
	public void modifyLivesLeft(int next){
		myStats.modifyLivesLeft(next);
	}
	public void modifyStrength(int next){
		myStats.setStrength(next);
	}
	public void modifyAgility(int next){
		myStats.setAgility(next);
	}
	public void modifyIntellect(int next){
		myStats.setIntellect(next);
	}
	public void modifyHardiness(int next){
		myStats.modifyHardiness(next);
	}
	public void modifyExperience(int next){
		myStats.modifyExperience(next);
	}
	public void modifyMovement(int next){
		myStats.modifyMovement(next);
	}
	public void modifyBindWounds(int next){
		myStats.modifyBindWounds(next);
	}
	public void modifyBargain(int next){
		myStats.setBargain(next);
	}
	public void modifyObservation(int next){
		myStats.setObservation(next);
	}
	public void modifyCurrentHP(int next){
		myStats.setCurrentHP(next);
	}
	public void modifyCurrentMP(int next){
		myStats.setCurrentMP(next);
	}
	public void modifyWeaponOffense(int next){
		myStats.setWeaponOffense(next);
	}
	public void modifyEquipArmor(int next){
		myStats.setEquipArmor(next);
	}
	
	/* -------------------- ABILITY ACCESSORS -------------------- */
	public void useAbility(int position){
		myAbilities.useAbility(position);
	}
	
    /* -------------------- MISC. ACCESSORS -------------------- */
    public int getCurrency(){
    	return currency;
    }
    public Direction getDirection() {
        return myDirection;
    }
    public MotionType getMotionType(){
    	return motionType;
    }

    /* -------------------- MISC. MUTATORS -------------------- */
    public void setCurrency(int newest){
    	currency = max(newest, 0);
    }
    public void modifyCurrency(int modifier){
    	currency = max(currency+modifier,0);
    }
    public void setMotionType(MotionType newest){
    	motionType = newest;
    }
}
