package model.effect;

import model.entity.Entity;


/**
 * @author Aaron Iglesias
 */
public class SnareEffect extends TemporaryEffect {
    int lifetime; //how long from now to revert, in milliseconds
    Entity entity;

    public SnareEffect(Entity entity, int time)
    {
        super(entity);
        this.entity = entity;
        this.lifetime = time;
        applyEffect();
        myTimer.addEvent(this, lifetime);
    }

    public void setLifeTime(int time)
    {
        this.lifetime = time;
    }    

    public int getLifeTime()
    {
        return lifetime;
    }

    public void setEntity(Entity entity)
    {
        this.entity = entity;
    }    

    public Entity getEntity()
    {
        return this.entity;
    }

    @Override
    public void run(){
        applyInverseEffect();
    }
    
    /**
     * applies the effect on the entity, to remain until it is inverted by applyInverseEffect
     * @author Aaron Iglesias
     */
    @Override
    protected void applyEffect()
    {
        entity.setMovementPermission(false);
    }
    
    /**
     * reverts the entity back to its original state
     * @author Aaron Iglesias
     */
    @Override
    protected void applyInverseEffect()
    {
        entity.setMovementPermission(true);
    }

    @Override
    public void applyEffect(Entity entityToAffect) {
        this.entity = entityToAffect;
        entity.setMovementPermission(false);
    }
}