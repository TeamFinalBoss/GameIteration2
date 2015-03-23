package view.viewport;

import java.awt.Color;
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
        int x = 0;
        for (Ability a : abilities) {
            if (a.getName() != null) {
                g.setColor(Color.red);
                g.drawString(a.getName().toString(), 300, GameDirector.getSize().height - 200 + x);
                x += 12;
            }
        }
    }

}
