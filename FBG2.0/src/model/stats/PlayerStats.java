package model.stats;

import model.util.Saveable;
import view.viewport.StatusViewPort;

public class PlayerStats extends Stats {

    private int currentHP;
    private int currentMP;
    private int level;
    private int livesLeft;
    private int experience;

    public PlayerStats(int level,
            int livesLeft,
            int strength,
            int agility,
            int intellect,
            int hardiness,
            int experience,
            int movement,
            int hpMax,
            int mpMax,
            int defense,
            int offense, int speed) {

        super(strength, agility, intellect, hardiness, movement, hpMax, mpMax, defense, offense, speed);

        this.currentHP = hpMax;
        this.currentMP = mpMax;
        this.level = level;
        this.livesLeft = livesLeft;
        this.experience = experience;
    }

    public PlayerStats() {
        this(1, 1, 1, 1, 1, 1, 1, 0, 100, 100, 1, 1, 1);
    }

    public int getHpCurrent() {
        return currentHP;
    }

    public int getMpCurrent() {
        return currentMP;
    }

    public int getLevel() {
        return level;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public int getExperience() {
        return experience;
    }

    public void modhpCurrent(int hpAdded) {
        currentHP += hpAdded;
    }

    public void modmpCurrent(int mpAdded) {
        currentMP += mpAdded;        
    }

    public void modLevel(int levelAdded) {
        level += levelAdded;
    }

    public void modLivesLeft(int livesAdded) {
        livesLeft += livesAdded;
    }

    public void modExperience(int experienceAdded) {
        experience += experienceAdded;
    }

    public void sethpCurrent(int hpNew) {
        currentHP = hpNew;
        this.setChanged();
        this.notifyObservers(this);
    }

    public void setmpCurrent(int mpNew) {
        currentMP = mpNew;
        this.setChanged();
        this.notifyObservers(this);
    }

    public void setLevel(int levelNew) {
        //hpCurrent = levelNew;
    }

    public void setLivesLeft(int livesNew) {
        //hpCurrent = livesNew;
    }

    public void setExperience(int experienceNew) {
        //hpCurrent = experienceNew;
    }
}
