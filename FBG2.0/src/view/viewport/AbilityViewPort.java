package view.viewport;

import controller.Controller;
import controller.keyBindings.KeyBindingsOption;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import model.director.AvatarInteractionManager;
import model.director.GameDirector;
import model.entity.ability.Ability;

/**
 *
 * @author ChrisMoscoso
 */
public class AbilityViewPort implements ViewPort {

    @Override
    public void draw(Graphics g) {
        List<Ability> abilities = AvatarInteractionManager.getInstance().getAvatar().getAllAbilities();
        int y = 0;
        int counter = 1;
        for (Ability a : abilities) {
            if (a.getName() != null) {
                g.setColor(Color.red);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                g.drawString("PRESS " + (counter >= 11 ? "-" : counter % 10) + " : " + a.getName().toString(), 280, GameDirector.getSize().height - 220 + y);
                y += 20;
                counter++;
            }
        }
    }

}
