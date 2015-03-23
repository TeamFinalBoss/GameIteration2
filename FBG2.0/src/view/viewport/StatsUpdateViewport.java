package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import model.director.GameDirector;
import controller.util.Healthable;

public class StatsUpdateViewport implements ViewPort, Observer {

    private static int currentValue;
    private static String[] options;
    private static int xStart;
    private static int screenWidth, screenHeight;
    private static int currentHealth, currentMana, maxHealth, maxMana;
    private static int yStart;
    private static int height = 16;
    private static int padding = 1;

    public StatsUpdateViewport() {
        screenWidth = GameDirector.getSize().width;
        screenHeight = GameDirector.getSize().height;
        xStart = screenWidth - (int) (GameDirector.getSize().getWidth() * 0.2);
        yStart = 0;
    }

    @Override
    public void draw(Graphics g) {

        int healthBarXMargin = (int) (screenWidth * 0.20);
        int healthBarYMargin = screenHeight - 135;
        int healthBarWidth = screenWidth - (healthBarXMargin * 2);
        int healthBarHeight = 25;

        double percentageOfHealthRemaining = Math.min((double) currentHealth / (double) maxHealth, 1.0);

        //Health bar
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(healthBarXMargin, healthBarYMargin, healthBarWidth, healthBarHeight, 25, 25);

        g.setColor(Color.GREEN);
        g.fillRoundRect(healthBarXMargin, healthBarYMargin, (int) (healthBarWidth * percentageOfHealthRemaining), healthBarHeight, 25, 25);

        String health = currentHealth + "/" + maxHealth;
        g.setColor(Color.BLACK);
        g.drawString(health, healthBarWidth / 2 + healthBarXMargin - g.getFontMetrics().stringWidth(health) / 2, healthBarYMargin + g.getFontMetrics().getHeight());

        //Mana bar
        int manaBarXMargin = (int) (screenWidth * 0.25);
        int manaBarWidth = screenWidth - (manaBarXMargin * 2);
        int manaBarYMargin = healthBarYMargin + healthBarHeight;
        int manaBarHeight = 25;

        double percentageOfManaRemaining = Math.min((double) currentMana / (double) maxHealth, 1.0);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(manaBarXMargin, manaBarYMargin, manaBarWidth, manaBarHeight, 25, 25);

        g.setColor(Color.BLUE);
        g.fillRoundRect(manaBarXMargin, manaBarYMargin, (int) (manaBarWidth * percentageOfManaRemaining), manaBarHeight, 25, 25);

        String mana = currentMana + "/" + maxMana;
        g.setColor(Color.WHITE);
        g.drawString(mana, manaBarWidth / 2 + manaBarXMargin - g.getFontMetrics().stringWidth(mana) / 2, manaBarYMargin + g.getFontMetrics().getHeight());

        if (options != null) {

            int height = g.getFontMetrics().getHeight();
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(xStart, yStart - height - padding, (int) (GameDirector.getSize().getWidth() * 0.2), (options.length) * (padding + height));
            for (int i = 0; i < options.length; i++) {
                g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN, 15));
                if (i == currentValue) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.black);
                }
                if (i == 13) {
                    g.setFont(new Font(g.getFont().getFamily(), Font.BOLD, 15));
                }
                String[] strings = options[i].split("\t");
                if (strings.length == 2) {
                    if (i == 0) {
                        g.setFont(new Font(g.getFont().getFamily(), Font.BOLD, 15));
                    }
                    g.drawString(strings[0], xStart, yStart + i * (padding + height));

                    g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN, 15));
                    int width = g.getFontMetrics().stringWidth(strings[1]);
                    g.drawString(strings[1], screenWidth - width - 5, yStart + i * (padding + height));
                } else {
                    g.drawString(options[i], xStart, yStart + i * (padding + height));
                }

            }
        }

    }

    @Override
    public void update(Observable arg0, Object arg1) {
        Healthable desc = (Healthable) arg0;
        currentHealth = desc.getCurrentHealth();
        currentMana = desc.getCurrentMana();
        maxHealth = desc.getMaxHealth();
        maxMana = desc.getMaxMana();
        options = desc.getDescription();
        yStart = GameDirector.getSize().height - (options.length * (height + padding));
        currentValue = desc.getCurrentIndex() + 1;
    }
}
