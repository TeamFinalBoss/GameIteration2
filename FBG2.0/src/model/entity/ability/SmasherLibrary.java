package model.entity.ability;

import model.entity.Entity;

public class SmasherLibrary extends AbilityLibrary {
    public SmasherLibrary(Entity owner){
            super(owner);
            
            CleaveAbility myCleave = new CleaveAbility();
                
            this.addToLibrary(myCleave);
    }
    
    public void update(){

    }
}
