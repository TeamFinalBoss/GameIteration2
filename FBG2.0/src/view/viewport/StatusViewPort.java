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

            int healthBarXMargin = (int) (width * 0.05);
            int healthBarYMargin = 35;
            int healthBarWidth = width - (healthBarXMargin * 2);
            int healthBarHeight = 25;
            
            double percentageOfHealthRemaining = (double) stats.getHpCurrent() / (double) stats.getMaxHP();
            
            //Health bar
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(healthBarXMargin , healthBarYMargin, healthBarWidth, healthBarHeight);
            
            g.setColor(Color.GREEN);
            g.fillRect(healthBarXMargin, healthBarYMargin, (int) (healthBarWidth * percentageOfHealthRemaining), healthBarHeight);
            
            String health = stats.getHpCurrent() + "/" + stats.getMaxHP();
            g.setColor(Color.BLACK);
            g.drawString(health, healthBarWidth/2 + healthBarXMargin + g.getFontMetrics().stringWidth(health ) /2, healthBarYMargin + g.getFontMetrics().getHeight());
            
            drawUglyStats(g);
            
            //Mana bar
            int manaBarXMargin = (int) (width * 0.15);
            int manaBarWidth = width - (manaBarXMargin * 2);
            int manaBarYMargin = healthBarYMargin + healthBarHeight;
            int manaBarHeight = 25;
            
            double percentageOfManaRemaining = (double) stats.getMpCurrent() / (double) stats.getMaxHP();
            
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(manaBarXMargin , manaBarYMargin, manaBarWidth, manaBarHeight);
            
            g.setColor(Color.BLUE);
            g.fillRect(manaBarXMargin, manaBarYMargin, (int) (manaBarWidth * percentageOfManaRemaining), manaBarHeight);
            
            String mana = stats.getMpCurrent() + "/" + stats.getMaxMP();
            g.setColor(Color.BLACK);
            g.drawString(mana, manaBarWidth/2 + manaBarXMargin + g.getFontMetrics().stringWidth(mana ) /2, manaBarYMargin + g.getFontMetrics().getHeight());
            

        }
    }
    
    /**
     * This is how we did it in iteration 1. Will be deprecated soon.
     * @param g 
     */
    public void drawUglyStats(Graphics g){
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
            g.drawString("HP Current: " + stats.getHpCurrent(), width - 150, height - 200);
            //g.drawString("HP Max: " + stats.getMaxHP(), width - 150, height - 180);
            //g.drawString("Level: " + stats.getLevels(), width - 150, height - 160);
            g.drawString("Lives: " + stats.getLivesLeft(), width - 150, height - 140);
            g.drawString("MP Current: " + stats.getMpCurrent(), width - 150, height - 120);
            g.drawString("MP Max: " + stats.getMaxHP(), width - 150, height - 100);
            g.drawString("Movement: " + stats.getMovement(), width - 150, height - 80);
            g.drawString("Offense: " + stats.getOffense(), width - 150, height - 60);
            g.drawString("Strength: " + stats.getStrength(), width - 150, height - 40);
            //g.drawString("Offensive Rating: " + stats.getOffensiveRating(), width - 150, height - 360);
    }

    @Override
    public void update(Observable o, Object arg) {
        stats = (PlayerStats) arg;
    }
}
