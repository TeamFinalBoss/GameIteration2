package model.effect;

import model.entity.Entity;


/**
 * TODO: add to this class when stat changes are implemented
 * Is an example OneTimeEffect class. Will probably be used by basic attacks.
 * @author Jason Owens
 */
public class DealDamageEffect implements Effect {
    int damageToDeal;
    int distance;

    public DealDamageEffect(int damageToDeal, int distance) {
        super();
        this.damageToDeal = damageToDeal;
        this.distance = distance;
    }
    
    public void setDamageToDeal(int damage){
        this.damageToDeal = damage;
    }
    
    public void setDistance(int distance)
    {
        this.distance = distance;
    }
    
    /**
     *
     * @param entityToAffect
     */
    @Override
    public void applyEffect(Entity entityToAffect){
        entityToAffect.dealDamage(damageToDeal / distance);
    }
}
