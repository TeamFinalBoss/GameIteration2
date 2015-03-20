package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import model.director.GameDirector;
import model.stats.PlayerStats;

/**
 *
 * @author ChrisMoscoso
 */
public class StatusViewPort implements ViewPort, Observer {

    private PlayerStats stats = new PlayerStats();
    
    private int width, height;

    @Override
    public void draw(Graphics g) {
        width = GameDirector.getSize().width;
        height = GameDirector.getSize().height;
        
        /*g.setColor(Color.blue);
         g.fillRect(0, height - 100, width, 100);
         g.setColor(Color.black);
         g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
         g.setColor(Color.black);
         g.drawString("STATUS VIEWPORT", width/2 - 85, height - 50);*/

        if (stats != null) {

            int healthBarMargin = (int) (width * 0.05);
            int healthBarWidth = width - (healthBarMargin * 2);
            
            double percentageOfHealthRemaining = 0.7;
            
            g.setColor(Color.green);
            g.drawRect(healthBarMargin , 35, healthBarWidth, 25);
            
            g.setColor(Color.magenta);
            g.fillRect(healthBarMargin, 35, (int) (healthBarWidth * percentageOfHealthRemaining), 25);
            
            g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(width - 160, height - 400, width, height);
            g.setColor(new Color(255, 255, 0, 255));
            g.drawString("Agility: " + stats.getAgility(), width - 150, height - 340);
            //g.drawString("Armor Rating: " +  stats.getArmorRating(), width - 150, height - 320);
            g.drawString("Defense: " + stats.getDefense(), width - 150, height - 300);
            //g.drawString("Defensive Rating: " + stats.getDefensiveRating(), width - 150, height - 280);
            g.drawString("Experience: " + stats.getExperience(), width - 150, height - 260);
            g.drawString("Hardiness: " + stats.getHardiness(), width - 150, height - 240);
            g.drawString("Intellect: " + stats.getIntellect(), width - 150, height - 220);
            //g.drawString("HP Current: " + stats.gethpCurrent(), width - 150, height - 200);
            g.drawString("HP Max: " + stats.gethpMax(), width - 150, height - 180);
            //g.drawString("Level: " + stats.getLevels(), width - 150, height - 160);
            g.drawString("Lives: " + stats.getLivesLeft(), width - 150, height - 140);
            //g.drawString("MP Current: " + stats.getmpCurrent(), width - 150, height - 120);
            g.drawString("MP Max: " + stats.getmpMax(), width - 150, height - 100);
            g.drawString("Movement: " + stats.getMovement(), width - 150, height - 80);
            g.drawString("Offense: " + stats.getOffense(), width - 150, height - 60);
            g.drawString("Strength: " + stats.getStrength(), width - 150, height - 40);
            //g.drawString("Offensive Rating: " + stats.getOffensiveRating(), width - 150, height - 360);

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        stats = (PlayerStats) arg;
    }
}
