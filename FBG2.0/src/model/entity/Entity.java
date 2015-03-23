package model.entity;

import model.entity.ability.AbilityLibrary;
import model.entity.ability.Ability;
import model.entity.inventory.Inventory;
import model.entity.stats.Stats;
import model.gameObject.MapObject;
import model.map.Direction;
import model.map.projectiles.Projectile;
import model.map.pair.CoordinatePair;
import model.map.areaEffect.AreaEffect;
import model.map.tile.Tile;
import model.map.tile.trap.Trap;
import model.util.GameTimer;
import model.effect.AllowMovement;
import model.effect.Dispellable;
import model.effect.Effect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.item.Item;
import model.item.Takeable;
import model.item.Equipable;
import model.item.EquipSlot;

import java.util.Map;

import model.director.ActiveMapManager;
import model.director.AvatarInteractionManager;

/**
 * The class Entity defines a common type for all entities (beings) in the game.
 *
 * ID: 1
 *
 * @see MapObject
 * @author Matthew Kroeze, Chris Moscoso, Michael Cohen
 * @version 1.1.0 2015-03-15
 */
public abstract class Entity extends MapObject {

    private Inventory myInventory;
    private AbilityLibrary myAbilities;
    private ArrayList<Dispellable> activeEffects;
    private Stats myStats;
    private Direction myDirection;
    private VisibleMap visibleMap;
    private ActiveMapManager activeMap; //forwards messages to the active map
    private int currency;
    private MotionType motionType;
    private boolean canMove;
    private String occupation;
    private String type;

    /* -------------------- PROTECTED COMPONENT CREATION -------------------- */
    protected Inventory createInventory() {
        return new Inventory(10, this);
    }

    protected abstract AbilityLibrary createAbilities();

    protected abstract Stats createStats();

    protected abstract String setOccupation();

    protected abstract String setType();

    /* -------------------- PROTECTED UTILITY -------------------- */
    public Stats getStats() {
        return myStats;
    }

    protected Inventory getInventory() {
        return myInventory;
    }

    protected AbilityLibrary getAbilities() {
        return myAbilities;
    }

    protected Direction motionToDirection(CoordinatePair change) {
        if (change.getX() > 0 && change.getY() > 0) {
            return Direction.NorthEast;
        }
        if (change.getX() < 0 && change.getY() > 0) {
            return Direction.NorthWest;
        }
        if (change.getX() > 0 && change.getY() < 0) {
            return Direction.SouthEast;
        }
        if (change.getX() < 0 && change.getY() < 0) {
            return Direction.SouthWest;
        }
        if (change.getX() < 0) {
            return Direction.West;
        }
        if (change.getX() > 0) {
            return Direction.East;
        }
        if (change.getY() > 0) {
            return Direction.North;
        }
        if (change.getY() < 0) {
            return Direction.South;
        }
        return myDirection;
    }

    /* -------------------- CONSTRUCTORS --------------------*/
    public Entity(String objectName,
            String description,
            CoordinatePair location) {
        super(objectName, description, location);
        myInventory = createInventory();
        myAbilities = createAbilities();
        myStats = createStats();
        myDirection = Direction.North;
        activeMap = ActiveMapManager.getInstance();
        currency = 0;
        motionType = MotionType.GROUND;
        activeEffects = new ArrayList<Dispellable>();
        visibleMap = new VisibleMap(this);
        canMove = true;
        this.setID("1");
        this.setClassName("Entity");
        visibleMap.update();
        occupation = setOccupation();
        type = setType();
    }

    /* -------------------- PRIVATE UTILITY -------------------- */
    private int max(int a, int b) {
        return a > b ? a : b;
    }
    
    public CoordinatePair tileInFront(){
    	CoordinatePair direction = new CoordinatePair(0,0);
    		switch(myDirection){
    		case North: direction = new CoordinatePair(0,1);
    					break;
    		case West:	direction = new CoordinatePair(-1,0);
						break;
    		case NorthWest:direction = new CoordinatePair(-1,1);
							break;
    		case East:	direction = new CoordinatePair(1,0);
						break;
    		case South: direction = new CoordinatePair(0,-1);
						break;
    		case NorthEast: direction = new CoordinatePair(1,1);
							break;
    		case SouthEast: direction = new CoordinatePair(1,-1);
							break;
    		case SouthWest: direction = new CoordinatePair(-1,-1);
							break;
    	}
    		direction.add(getLocation());
    		return direction;
    }
    public double getConfoundingFactor(){
    	Random generator = new Random();
    	return generator.nextDouble() >= .5 ? 1 + generator.nextDouble() / getObservation() : 1 - generator.nextDouble() / getObservation();
    }

    /* -------------------- INVENTORY ACCESSORS --------------------*/
    public List<Takeable> getSackContents() {
        return myInventory.sackContents();
    }

    public int getSackSize() {
        return myInventory.sackSize();
    }

    public int getSackCapacity() {
        return myInventory.sackCapacity();
    }

    public Map<EquipSlot, Equipable> getArmoryContents() {
        return myInventory.armoryContents();
    }

    /* -------------------- INVENTORY MUTATORS ---------------------*/
    public boolean use(int position) {
        return myInventory.use(position);
    }

    public Takeable remove(int position) {
        return myInventory.remove(position);
    }

    public boolean insert(Takeable next) {
        return myInventory.insert(next);
    }

    public void equip(Equipable next) {
        myInventory.equip(next);
        remove(next);
    }

    public Equipable unequip(EquipSlot slot) {
    	Equipable value = myInventory.unequip(slot);
    	insert(value);
        return value;
    }

    public Takeable remove(Takeable item) {
        return myInventory.remove(item);
    }

    public void drop(int position) {
        activeMap.addItemToActiveMap(remove(position), new CoordinatePair(getLocation().getX(),getLocation().getY()));
    }

    /* -------------------- STATS ACCESSORS -------------------- */
    public int getLivesLeft() {
        myAbilities.update();
        return myStats.livesLeft();

    }

    public int getStrength() {
        myAbilities.update();
        return myStats.strength();

    }

    public int getAgility() {
        myAbilities.update();
        return myStats.agility();

    }

    public int getIntellect() {
        myAbilities.update();
        return myStats.intellect();

    }

    public int getHardiness() {
        myAbilities.update();
        return myStats.hardiness();

    }

    public int getExperience() {
        myAbilities.update();
        return myStats.experience();

    }

    public int getMovement() {
        myAbilities.update();
        return myStats.movement();

    }

    public int getLevel() {
        myAbilities.update();
        return myStats.level();

    }

    public int getMaxHP() {
        myAbilities.update();
        return myStats.maxHealth();

    }

    public int getMaxMP() {
        myAbilities.update();
        return myStats.maxMana();

    }

    public int getOffense() {
        myAbilities.update();
        return myStats.offense();

    }

    public int getDefense() {
        myAbilities.update();
        return myStats.defense();

    }

    public int getArmor() {
        myAbilities.update();
        return myStats.armor();

    }

    public int getBindWounds() {
        myAbilities.update();
        return myStats.bindWounds();

    }

    public int getBargain() {
        myAbilities.update();
        return myStats.bargain();

    }

    public int getObservation() {
        myAbilities.update();
        return myStats.observation();

    }

    public int getCurrentHP() {
        myAbilities.update();
        return myStats.getCurrentHealth();

    }

    public int getCurrentMP() {
        myAbilities.update();
        return myStats.getCurrentMana();

    }

    public int getWeaponOffense() {
        myAbilities.update();
        return myStats.weaponOffense();

    }

    public int getEquipArmor() {
        myAbilities.update();
        return myStats.equipArmor();

    }

    /* -------------------- STATS SET MUTATORS -------------------- */
    public void setLivesLeft(int next) {
        myStats.setLivesLeft(next);
        myAbilities.update();
    }

    public void setStrength(int next) {
        myStats.setStrength(next);
        myAbilities.update();
    }

    public void setAgility(int next) {
        myStats.setAgility(next);
        myAbilities.update();
    }

    public void setIntellect(int next) {
        myStats.setIntellect(next);
        myAbilities.update();
    }

    public void setHardiness(int next) {
        myStats.setHardiness(next);
        myAbilities.update();
    }

    public void setExperience(int next) {
        int initial = getLevel();
        myStats.setExperience(next);
        if (initial > getLevel()) {
            AvatarInteractionManager.getInstance().modifySkillPoints(10);
            AvatarInteractionManager.getInstance().modifyStatPoints(10);
        }
        myAbilities.update();
    }

    public void setMovement(int next) {
        myStats.setMovement(next);
        myAbilities.update();
    }

    public void setBindWounds(int next) {
        myStats.setBindWounds(next);
        myAbilities.update();
    }

    public void setBargain(int next) {
        myStats.setBargain(next);
        myAbilities.update();
    }

    public void setObservation(int next) {
        myStats.setObservation(next);
        myAbilities.update();
    }

    public void setCurrentHP(int next) {
        myStats.setCurrentHealth(next);
        if (getCurrentHP() <= 0) {
            die();
        }
        myAbilities.update();
    }

    public void setCurrentMP(int next) {
        myStats.setCurrentMana(next);
        myAbilities.update();
    }

    public void setWeaponOffense(int next) {
        myStats.setWeaponOffense(next);
        myAbilities.update();
    }

    public void setEquipArmor(int next) {
        myStats.setEquipArmor(next);
        myAbilities.update();
    }

    /* -------------------- STATS MODIFY MUTATORS -------------------- */
    public void dealDamage(int amount) {
        myStats.dealDamage(amount);
        if (getCurrentHP() <= 0) {
            die();
        }
        myAbilities.update();
    }

    public void levelUp() {
        myStats.levelUp();
        AvatarInteractionManager.getInstance().modifySkillPoints(10);
        AvatarInteractionManager.getInstance().modifyStatPoints(10);
        myAbilities.update();
    }

    public void modifyLivesLeft(int next) {
        myStats.modifyLivesLeft(next);
        myAbilities.update();
    }

    public void modifyStrength(int next) {
        myStats.modifyStrength(next);
        myAbilities.update();
    }

    public void modifyAgility(int next) {
        myStats.modifyAgility(next);
        myAbilities.update();
    }

    public void modifyIntellect(int next) {
        myStats.modifyIntellect(next);
        myAbilities.update();
    }

    public void modifyHardiness(int next) {
        myStats.modifyHardiness(next);
        myAbilities.update();
    }

    public void modifyExperience(int next) {
        int initial = getLevel();
        myStats.modifyExperience(next);
        if (initial > getLevel()) {
            AvatarInteractionManager.getInstance().modifySkillPoints(10);
            AvatarInteractionManager.getInstance().modifyStatPoints(10);
        }
        myAbilities.update();
    }

    public void modifyMovement(int next) {
        myStats.modifyMovement(next);
        myAbilities.update();
    }

    public void modifyBindWounds(int next) {
        myStats.modifyBindWounds(next);
        myAbilities.update();
    }

    public void modifyBargain(int next) {
        myStats.modifyBargain(next);
        myAbilities.update();
    }

    public void modifyObservation(int next) {
        myStats.modifyObservation(next);
        myAbilities.update();
    }

    public void modifyCurrentHP(int next) {
        myStats.modifyCurrentHP(next);
        if (getCurrentHP() <= 0) {
            die();
        }
        myAbilities.update();
    }

    public void modifyCurrentMP(int next) {
        myStats.modifyCurrentMP(next);
        myAbilities.update();
    }

    public void modifyWeaponOffense(int next) {
        myStats.modifyWeaponOffense(next);
        myAbilities.update();
    }

    public void modifyEquipArmor(int next) {
        myStats.modifyEquipArmor(next);
        myAbilities.update();
    }

    /* -------------------- ABILITY ACCESSORS -------------------- */
    public List<Ability> getAllAbilities() {
        return myAbilities.getAbilities();
    }

    /* -------------------- ABILITY USE -------------------- */
    public void useAbility(int position) {
        myAbilities.performActiveAbility(position, this);
    }

    /* -------------------- VISIBLE MAP ACCESSORS -------------------- */
    public List<Tile> getVisibleTiles() {
        return visibleMap.getVisibleTiles();
    }

    public List<Projectile> getVisibleProjectiles() {
        return visibleMap.getVisibleProjectiles();
    }

    public List<Entity> getVisibleEntities() {
        return visibleMap.getVisibleEntities();
    }

    public List<Trap> getVisibleTraps() {
        return visibleMap.getVisibleTraps();
    }

    public List<Item> getVisibleItems() {
        return visibleMap.getVisibleItems();
    }

    public List<AreaEffect> getVisibleAreaEffects() {
        return visibleMap.getVisibleAreaEffects();
    }

    /* -------------------- ACTIVE EFFECT MUTATORS -------------------- */
    public void addEffect(Dispellable effect) {
        activeEffects.add(effect);
    }

    public void removeEffect(Dispellable effect) {
        activeEffects.remove(effect);
    }

    public List<Dispellable> getEffects() {
        return activeEffects;
    }

    /* -------------------- MISC. ACCESSORS -------------------- */
    public int getCurrency() {
        return currency;
    }

    public Direction getDirection() {
        return myDirection;
    }

    public MotionType getMotionType() {
        return motionType;
    }

    public boolean canSee(int observation) {
    	return true;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getType() {
        return type;
    }

    /* -------------------- MISC. MUTATORS -------------------- */
    public void die() {
        ActiveMapManager.getInstance().removeEntityFromActiveMap(this);
        modifyLivesLeft(-1);
        if(getLivesLeft() > 0) ActiveMapManager.getInstance().addEntityToActiveMap(this, getLocation());
    }

    public void setCurrency(int newest) {
        currency = max(newest, 0);
    }

    public void modifyCurrency(int modifier) {
        currency = max(currency + modifier, 0);
    }

    public void setMotionType(MotionType newest) {
        motionType = newest;
    }

    public boolean modifyLocation(CoordinatePair change) {
    	setDirection(motionToDirection(change));
    	super.modifyLocation(change);
    	if (!canMove) {
            return false;
        }
        canMove = false;
        GameTimer.getInstance().addEvent(new AllowMovement(this), (int) 10000 / getMovement());
        this.visibleMap.update();
        
        this.modifyCurrentMP(10);
        return true;
    }

    public void setMovementPermission(boolean newest) {
        canMove = newest;
    }
    public boolean getMovementPermission(){
    	return canMove;
    }

    public void setDirection(Direction newest) {
        myDirection = newest;
    }
    
    public void updateVisibleMap(){
        this.visibleMap.update();
    }
    

    public void useWeapon() {
        this.myInventory.useWeapon(this);
    }
}
