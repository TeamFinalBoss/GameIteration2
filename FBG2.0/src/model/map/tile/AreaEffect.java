package model.map.tile;

import model.ability.Effect;
import model.entity.Entity;

public abstract class AreaEffect {

    Effect effect;

    public AreaEffect() {
        effect = null;
    }

    /**
     *
     * @param e
     */
    public AreaEffect(Effect e) {
        effect = e;
    }

    /**
     * Method to be overridden by subclasses to provide unique functionality to
     * each AreaEffect when touched.
     *
     * @author Michael Cohen
     * @param caller the entity that activated the trap
     * @see Entity
     */
    public abstract void activate(Entity caller);
}
