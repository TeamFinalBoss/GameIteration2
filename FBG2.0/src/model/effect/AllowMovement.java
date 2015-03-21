package model.effect;

import model.effect.AllowMovement;
import java.util.TimerTask;
import model.entity.Entity;

public class AllowMovement extends TimerTask {
	private Entity target;
	public AllowMovement(Entity target){
		this.target = target;
	}
	public void run(){
		target.setMovementPermission(true);
	}
}
