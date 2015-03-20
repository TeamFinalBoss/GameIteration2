package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import controller.sceneControllers.SceneType;
import controller.util.SceneObserver;
import controller.util.Selectable;

public class SackViewport implements ViewPort, Observer, SceneObserver {
	
	private int currentSelection;
	private final int itemsPerRow = 5;
	private boolean canDraw = false;
	
	public SackViewport() {
	}

	

	@Override
	public void draw(Graphics g) {
		if(canDraw) {
			for(int i = 0; i < itemsPerRow; ++i) {
				for(int j = 0; j < itemsPerRow; ++j) {
					if(i + itemsPerRow * j == currentSelection) {
						g.setColor(Color.blue);
					} else {
						g.setColor(Color.ORANGE);
					}
					g.drawRect(i * 31, j * 31, 31, 31);
				}
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Modify when I can do stuff with inventory.
		Selectable selection = (Selectable)arg0;
		currentSelection = selection.getCurrentIndex();
	}



	@Override
	public void update(SceneType type) {
		if((type == SceneType.ARMORY && canDraw == true) || type == SceneType.SACK ) {
			canDraw = true;
		} else {
			canDraw = false;
		}
	}

}
