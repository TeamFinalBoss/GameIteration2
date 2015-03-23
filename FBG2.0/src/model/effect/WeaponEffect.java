/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.effect;

import model.entity.Entity;

/**
 *
 * @author ashishag
 */
public class WeaponEffect implements Effect{
    
    
    public void WeaponEffect(Entity ER){
        ER.applyEffect();
        
    }
        public void applyEffect();
        
}
