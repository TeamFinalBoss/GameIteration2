package model.entity.ability;

import model.director.ActiveMapManager;
import model.effect.Effect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;
import model.map.tile.trap.DealDamageTrap;
import model.map.tile.trap.Trap;

public class SetTrapAbility extends LinearAbility {
	
	private Trap myTrap;
	
	public SetTrapAbility(){
		 super();
		 this.setName("SetTrap");
		 this.myTrap = new DealDamageTrap();
		 this.setRange(1);
	}
	
	public SetTrapAbility(String name, Effect effect, Effect cost, Trap trap){
		super(name, effect, cost, 1);
		this.setName("SetTrap");
		this.myTrap = trap;
	}

	@Override
	public boolean meetsStatRequirements(Entity entityToLearn) {
		//return entityToLearn.getLevel() >= 2;
		return true;
	}

	@Override
	public boolean performAbility(Entity caster) {
		CoordinatePair CP = new CoordinatePair(caster.getLocation().getX(), caster.getLocation().getY());
		
		switch(caster.getDirection()){
		case North:
			CP.add(new CoordinatePair(0, 1));
			break;
		case NorthEast:
			CP.add(new CoordinatePair(1, 1));
			break;
		case East:
			CP.add(new CoordinatePair(1, 0));
			break;
		case SouthEast:
			CP.add(new CoordinatePair(1, -1));
			break;
		case South:
			CP.add(new CoordinatePair(0, -1));
			break;
		case SouthWest:
			CP.add(new CoordinatePair(-1, -1));
			break;
		case West:
			CP.add(new CoordinatePair(-1, 0));
			break;
		case NorthWest:
			CP.add(new CoordinatePair(-1, 1));
			break;
		default:
			CP.add(new CoordinatePair(0, 1));
			break;
		}
		if (caster.getCurrentMP() >= 10){
			caster.modifyCurrentMP(-10);
			myTrap.setLocation(CP);
			ActiveMapManager.getInstance().addTrapToActiveMap(myTrap, CP);
			return true;
		}		
		return false;
	}

}
