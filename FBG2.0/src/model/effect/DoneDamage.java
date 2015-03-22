/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.effect;

import model.entity.Entity;
import model.entity.stats.SmasherStats;
import model.entity.stats.SneakStats;
import model.entity.stats.SummonerStats;

/**
 * //Not complete; Matthew to assist;
 * @author ashishag
 */
public class DoneDamage extends DealDamageEffect {
    
 
    public DoneDamage(int damageToDeal) 
    {
        super();
        this.damageToDeal = damageToDeal;
    }
    
   
    
    public void dealDamage(int damageToDeal)
    {
        e.dealDamage(damageToDeal);
    }
    
    public void modifyBane(int damageToDeal)
    {
       bane = max(bane+ dealToDamage,0);        
    }
}
