package model.stats;

import java.util.Observable;
import java.util.Observer;
import model.util.Saveable;

public class Stats extends Observable {

    protected int strength;
    protected int agility;
    protected int intellect;
    protected int hardiness;
    protected int movement;
    protected int maxHP;
    protected int maxMP;
    protected int speed;
    protected int defense;
    protected int offense;

    public Stats(int strength,
            int agility,
            int intellect,
            int hardiness,
            int movement,
            int maxHP,
            int maxMP,
            int defense,
            int offense, int speed) {
        this.strength = strength;
        this.agility = agility;
        this.intellect = intellect;
        this.hardiness = hardiness;
        this.movement = movement;
        this.maxHP = maxHP;
        this.maxMP = maxMP;
        this.defense = defense;
        this.offense = offense;
        this.speed = speed;
    }

    public Stats() {
        this(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntellect() {
        return intellect;
    }

    public int getHardiness() {
        return hardiness;
    }

    public int getMovement() {
        return movement;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public int getOffense() {
        return offense;
    }

    public int getDefense() {
        return defense;
    }

    public int getHpMax() {
        return maxHP;
    }

    public int getMpMax() {
        return maxMP;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHpMax(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setMpMax(int maxMP) {
        this.maxMP = maxMP;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    

    public void setStrength(int nextStr) {
        strength = verifyBounds(nextStr) ? nextStr : strength;
    }

    public void setAgility(int nextAgi) {
        agility = verifyBounds(nextAgi) ? nextAgi : agility;
    }

    public void setIntellect(int nextInt) {
        intellect = verifyBounds(nextInt) ? nextInt : intellect;
    }

    public void setHardiness(int nextHard) {
        hardiness = verifyBounds(nextHard) ? nextHard : hardiness;
    }

    public void setMovement(int nextMove) {
        movement = verifyBounds(nextMove) ? nextMove : movement;
    }

    public void setMaxHP(int nextHP) {
        maxHP = nextHP;
    }

    public void setMaxMP(int nextMP) {
        maxMP = verifyBounds(nextMP) ? nextMP : maxMP;
    }

    public void setDefense(int nextDef) {
        defense = verifyBounds(nextDef) ? nextDef : defense;
    }

    public void setOffense(int nextOff) {
        offense = verifyBounds(nextOff) ? nextOff : offense;
    }

    public void modStrength(int strAdded) {
        strength = verifyBounds(strength + strAdded) ? (strength + strAdded) : 0;
    }

    public void modAgility(int agiAdded) {
        agility = verifyBounds(agility + agiAdded) ? (agility + agiAdded) : 0;
    }

    public void modIntellect(int intAdded) {
        intellect = verifyBounds(intellect + intAdded) ? (intellect + intAdded) : 0;
    }

    public void modHardiness(int hardAdded) {
        hardiness = verifyBounds(hardiness + hardAdded) ? (hardiness + hardAdded) : 0;
    }

    public void modMovement(int moveAdded) {
        movement = verifyBounds(movement + moveAdded) ? (movement + moveAdded) : 0;
    }

    public void modmaxHP(int hpAdded) {
        maxHP += hpAdded;
    }

    public void modmaxMP(int mpAdded) {
        maxMP = verifyBounds(maxMP + mpAdded) ? (maxMP + mpAdded) : 0;
    }

    public void modOffense(int offAdded) {
        offense = verifyBounds(offense + offAdded) ? (offense + offAdded) : 0;
    }

    public void modDefense(int defAdded) {
        defense = verifyBounds(defense + defAdded) ? (defense + defAdded) : 0;
    }

    public Stats inverted() {
        return new Stats(strength * -1, agility * -1, intellect * -1, hardiness * -1, movement * -1, maxHP * -1, maxMP * -1, defense * -1, offense * -1 , speed * -1);
    }

    protected boolean verifyBounds(int value) {
        return (value < 0) ? false : true;
    }

    public void mergeStats(Stats modifier) {
        modStrength(modifier.getStrength());
        modAgility(modifier.getAgility());
        modIntellect(modifier.getIntellect());
        modHardiness(modifier.getHardiness());
        modMovement(modifier.getMovement());
        modmaxHP(modifier.getMaxHP());
        modmaxMP(modifier.getMaxMP());
        modOffense(modifier.getOffense());
        modDefense(modifier.getDefense());
        this.hasChanged();
        this.notifyObservers(this);
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        this.hasChanged();
        this.notifyObservers(this);
    }
}
