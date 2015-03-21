package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import controller.sceneControllers.SceneType;
import controller.util.SceneObserver;
import controller.util.Selectable;

public class ArmoryViewport implements ViewPort, Observer, SceneObserver {
	
	private int currentSelection;
	private boolean canDraw;
	private SceneType lastType;
	private SceneType currentType;
	private boolean inArmory;

	public ArmoryViewport() {
		canDraw = false;
		currentSelection = 0;
	}
	
	@Override
	public void draw(Graphics g) {
		if(canDraw) {
			setColor(g);
			//HEAD
			if(currentSelection == 0) {
				setActiveColor(g);
			}
			g.drawRect(50, 0, 50, 50);
			//CHEST
			setColor(g);
			if(currentSelection == 1) {
				setActiveColor(g);
			}
			g.drawRect(50,50, 50,50);
			//MAIN HAND
			setColor(g);
			if(currentSelection == 2) {
				setActiveColor(g);
			}
			g.drawRect(0,50,50,50);
			//OFF HAND
			setColor(g);
			if(currentSelection == 3) {
				setActiveColor(g);
			}
			g.drawRect(100,50,50,50);
			//LEGS
			setColor(g);
			if(currentSelection == 4) {
				setActiveColor(g);
			}
			
			g.drawRect(50,100,50,50);
			//FEET
			setColor(g);
			if(currentSelection == 5) {
				setActiveColor(g);
			}
			g.drawRect(50,150,50,50);
		}

	}

	private void setActiveColor(Graphics g) {
		g.setColor(Color.BLUE);
		
	}

	private void setColor(Graphics g) {
		g.setColor(Color.BLACK);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Selectable select = (Selectable) arg0;
		this.currentSelection = select.getCurrentIndex();
	}

	@Override
	public void update(SceneType type) { 
		if(!canDraw) {
			if(type.equals(SceneType.ARMORY)) {
				canDraw = true;
			}
		} else {
			if(type.equals(SceneType.GAME) || (currentType.equals(SceneType.ARMORY) && type.equals(SceneType.SACK))) {
				canDraw = false;
			}
		}
		
		currentType = type;
	}


}
