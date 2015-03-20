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
	private int numberOfItems = 50;
	private final int maxNumberOfItemsDrawn = 50;
	private int currentMaxRow = 4;
	private int currentMinRow = 0;
	private boolean canDraw = false;
	private int maximumNumberOfRows = 5;
	
	public SackViewport() {
	}

	

	@Override
	public void draw(Graphics g) {
		if(canDraw) {
			//I is the row J is the column
			for(int i = 0; i < itemsPerRow; ++i) {
				for(int j = 0; j < maximumNumberOfRows; ++j) {
					//actually draw item using i, j, items per row and other shit.
					drawItem(g,i,j);				
				}
			}
		}
	}
	
	private void drawItem(Graphics g, int i, int j) {
		if((i * itemsPerRow + j) + (itemsPerRow * currentMinRow) == currentSelection) {
			g.setColor(Color.blue);
		} else {
			g.setColor(Color.ORANGE);
		}
		g.drawRect(j * 31, i * 31, 31, 31);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Modify when I can do stuff with inventory.
		Selectable selection = (Selectable)arg0;
		currentSelection = selection.getCurrentIndex();
		if(currentSelection/itemsPerRow > currentMaxRow) {
			++currentMinRow;
			++currentMaxRow;
		} else if(currentSelection/itemsPerRow < currentMinRow){
			--currentMinRow;
			--currentMaxRow;
		}
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
