package model.entity.ability;

import model.effect.Effect;
import model.entity.Entity;
import model.map.Direction;
import model.map.pair.CoordinatePair;

public class BlinkAbility extends LinearAbility {
	
	public BlinkAbility(){
		super();
		this.setName("Blink");
	}
	
	public BlinkAbility(String name, Effect effect, Effect cost, double range){
		super(name, effect, cost, range);
		this.setName("Blink");
	}

	@Override
	public boolean meetsStatRequirements(Entity entityToLearn) {
		return entityToLearn.getLevel() >= 2;
	}

	@Override
	public boolean performAbility(Entity caster) {
		//this.getCost().applyEffect(caster); Michael: not really sure how costs work
		CoordinatePair CP = null;
		switch(caster.getDirection()){
		case North:
			CP = new CoordinatePair(0, 5);
			break;
		case NorthEast:
			CP = new CoordinatePair(2, 3);
			break;
		case East:
			CP = new CoordinatePair(5, 0);
			break;
		case SouthEast:
			CP = new CoordinatePair(2, -3);
			break;
		case South:
			CP = new CoordinatePair(0, -5);
			break;
		case SouthWest:
			CP = new CoordinatePair(-2, -3);
			break;
		case West:
			CP = new CoordinatePair(-5, 0);
			break;
		case NorthWest:
			CP = new CoordinatePair(-2, 3);
			break;
		default:
			CP = new CoordinatePair();
			break;
		}
		
		caster.modifyLocation(CP);
		return true; //TODO: Michael: not really sure how to apply the consequences of this.cost which would make this method fail
	}

}
