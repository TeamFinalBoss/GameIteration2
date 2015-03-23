package model.entity.ability;

import java.io.FileInputStream;
import java.io.InputStream;
import model.effect.Effect;
import model.entity.Entity;

import model.map.Direction;
import model.director.CombatCoordinator;
import model.map.pair.CoordinatePair;
import java.util.ArrayList;
import model.entity.Entity;
import java.lang.Math.*;
import java.util.List;
import model.director.ActiveMapManager;
import model.effect.DealDamageEffect;
import model.entity.ability.ProjectileAbility;
import model.map.GameMap;
import model.map.Locations;
import model.map.Vector;
import model.map.pair.PreciseCoordinatePair;
import model.map.projectiles.Fireball;
import model.map.projectiles.NinjaStar;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
*
* @author Aaron Iglesias
*/

public class FlameStrikeAbility extends AngularAbility
{
   
    
    
    private ActiveMapManager myMM;
    private int damage;

    
	public FlameStrikeAbility()
	{
		super();
		this.setName("Strike");
        this.damage = 10;
		this.setEffect(new DealDamageEffect(this.damage));
		this.setRadius(3);
		this.myMM = ActiveMapManager.getInstance();
        this.setDegree(360);
        
	}

	public FlameStrikeAbility(String name, Effect effect, Effect cost, int degree, double radius, int distance)
	{
		super(name, effect, cost, degree, radius);
		this.myMM = ActiveMapManager.getInstance();
        this.setName("FlameStrike");
	}

	@Override
	public boolean meetsStatRequirements(Entity caster)
	{
		/*
		if(caster.getStrength() >= 15)
            return true;
        else
            return false;*/
		return true;
	}

	@Override
    public boolean performAbility(Entity caster) 
    {
         try {
            InputStream in = new FileInputStream("src/resources/sound/pewpew.wav");
            AudioStream as = new AudioStream(in);

            AudioPlayer.player.start(as);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        
    	GameMap map = myMM.getActiveMap();
    	int mana = caster.getCurrentMP();
    	List<Entity> entities = map.getEntities();
        CoordinatePair c1 = caster.getLocation();
        CoordinatePair c2;
        int manaCost = this.damage;
        int distance;

    	if(mana >= manaCost)
    	{
                /*
    		caster.setCurrentMP(mana - manaCost);
    		for(int i = 0; i < entities.size(); ++i)
    		{
    			if(inRange(caster, entities.get(i)))
                        {
                            c2 = entities.get(i).getLocation();
                            if(c1 != c2)
                            {
                                distance = (int) c1.getDistance(c1,c2);
                                ((DealDamageEffect)this.getEffect()).applyEffect(entities.get(i),distance);
                            }
                        }
    		}*/
                
                caster.modifyCurrentMP(-1);
            
                CoordinatePair coordinatePair = caster.getLocation();
                double x = coordinatePair.getX();
                double y = coordinatePair.getY();

                PreciseCoordinatePair PCP = new PreciseCoordinatePair();
                PCP.set(x,y);
                
                Vector velocity = new Vector(caster.getDirection());
                velocity.multiply(3);
                
                
                
                Vector v1 = new Vector(Direction.East);
                Vector v2 = new Vector(Direction.West);
                Vector v3 = new Vector(Direction.North);
                Vector v4 = new Vector(Direction.South);
                Vector v5 = new Vector(Direction.SouthEast);
                Vector v6 = new Vector(Direction.NorthEast);
                Vector v7 = new Vector(Direction.SouthWest);
                Vector v8 = new Vector(Direction.NorthWest);
                
                PreciseCoordinatePair PCP1 = new PreciseCoordinatePair(PCP.getX(),PCP.getY());
                PreciseCoordinatePair PCP2 = new PreciseCoordinatePair(PCP.getX(),PCP.getY());
                PreciseCoordinatePair PCP3 = new PreciseCoordinatePair(PCP.getX(),PCP.getY());
                PreciseCoordinatePair PCP4 = new PreciseCoordinatePair(PCP.getX(),PCP.getY());
                PreciseCoordinatePair PCP5 = new PreciseCoordinatePair(PCP.getX(),PCP.getY());
                PreciseCoordinatePair PCP6 = new PreciseCoordinatePair(PCP.getX(),PCP.getY());
                PreciseCoordinatePair PCP7 = new PreciseCoordinatePair(PCP.getX(),PCP.getY());
                PreciseCoordinatePair PCP8 = new PreciseCoordinatePair(PCP.getX(),PCP.getY());
                
                System.out.println(caster.getName());
                
                if(caster.getName().equals( "The Summoner")){
                
                    Fireball fb1 = new Fireball((long)2000, v1, PCP1, getEffect(), caster);
                    Fireball fb2 =new Fireball((long)2000, v2, PCP2, getEffect(), caster);
                    Fireball fb3 =new Fireball((long)2000, v3, PCP3, getEffect(), caster);
                    Fireball fb4 =new Fireball((long)2000, v4, PCP4, getEffect(), caster);
                    Fireball fb5 =new Fireball((long)2000, v5, PCP5, getEffect(), caster);
                    Fireball fb6 =new Fireball((long)2000, v6, PCP6, getEffect(), caster);
                    Fireball fb7 =new Fireball((long)2000, v7, PCP7, getEffect(), caster);
                    Fireball fb8 =new Fireball((long)2000, v8, PCP8, getEffect(), caster);
                
                }
                else{
                    NinjaStar fb1 = new NinjaStar((long)2000, v1, PCP1, getEffect(), caster);
                    NinjaStar fb2 =new NinjaStar((long)2000, v2, PCP2, getEffect(), caster);
                    NinjaStar fb3 =new NinjaStar((long)2000, v3, PCP3, getEffect(), caster);
                    NinjaStar fb4 =new NinjaStar((long)2000, v4, PCP4, getEffect(), caster);
                    NinjaStar fb5 =new NinjaStar((long)2000, v5, PCP5, getEffect(), caster);
                    NinjaStar fb6 =new NinjaStar((long)2000, v6, PCP6, getEffect(), caster);
                    NinjaStar fb7 =new NinjaStar((long)2000, v7, PCP7, getEffect(), caster);
                    NinjaStar fb8 =new NinjaStar((long)2000, v8, PCP8, getEffect(), caster);
                }
                
    		return true;
                
    	}
    	else
    		return false;
    }

}