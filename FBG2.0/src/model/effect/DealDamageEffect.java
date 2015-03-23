package model.effect;

import model.entity.Entity;
import java.lang.Math.*;


/**
 * TODO: add to this class when stat changes are implemented
 * Is an example OneTimeEffect class. Will probably be used by basic attacks.
 * @author Jason Owens
 */
public class DealDamageEffect implements Effect {
    int damageToDeal;
    

    public DealDamageEffect(int damageToDeal) {
        super();
        this.damageToDeal = damageToDeal;
        
    }
    
    public void setDamageToDeal(int damage){
        this.damageToDeal = damage;
    }
    
    
    protected int getDamageToDeal(){ return damageToDeal;}
    
    
    /**
     *
     * @param entityToAffect
     */
    public void applyEffect(Entity entityToAffect, int distance){
        if(distance < 1) // prevent divide by 0
            distance = 1;
        entityToAffect.dealDamage((int) (damageToDeal / Math.sqrt(distance)));
    }

    @Override
    public void applyEffect(Entity entityToAffect) {
        applyEffect(entityToAffect, 10);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
