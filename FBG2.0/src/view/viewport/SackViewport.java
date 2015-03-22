package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import view.MousePoint;
import controller.commands.sceneChangers.ArmorySackMaintainer;
import controller.util.Selectable;

public class SackViewport extends Observable implements ViewPort, Observer, MousePoint, Selectable {
	
	private int currentSelection;
	private final int itemsPerRow = 5;
	private int currentMaxRow = 4;
	private int currentMinRow = 0;
	private int maximumNumberOfRows = 5;

	public SackViewport() {

	}

	

	@Override
	public void draw(Graphics g) {
		if(canDraw()) {
			//I is the row J is the column
			for(int i = 0; i < maximumNumberOfRows; ++i) {
				for(int j = 0; j < itemsPerRow; ++j) {
					//actually draw item using i, j, items per row and other shit.
					drawItem(g,i,j);				
				}
			}
		}
	}
	
	private boolean canDraw() {
		return ArmorySackMaintainer.isPressedSack();
	}



	private void drawItem(Graphics g, int i, int j) {
		if((i * itemsPerRow + j) + (maximumNumberOfRows * currentMinRow) == currentSelection) {
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
	public int getCurrentIndex() {
		return this.currentSelection;
	}
	
	protected boolean withinYBounds(int i, int y) {
		int heightUpperBounds = i*31;
		int heightLowerBounds = heightUpperBounds + 31;
		System.out.println("Height: " + heightUpperBounds + " " + heightLowerBounds + " " + y);
		return ((y <= heightLowerBounds) && (y >= heightUpperBounds));
	}

	protected boolean withinXBounds(int j, int x) {
		int widthLeftBounds = j * 31;
		int widthRightBounds = widthLeftBounds + 31;
		System.out.println("Width: " + widthLeftBounds + " " + widthRightBounds + " " + x);
		return ((x >= widthLeftBounds) && (x <= widthRightBounds));
	}



	@Override
	public void getActiveLocation(Point point) {
		for(int i = 0; i < maximumNumberOfRows; ++i) {
			for(int j = 0; j < itemsPerRow; ++j) {
				if(withinXBounds(j,(int)point.getX()) && withinYBounds(i,(int)point.getX())) {		
					currentSelection = (i * itemsPerRow + j) + (maximumNumberOfRows * currentMinRow);
					setChanged();
					notifyObservers();
				}
			}
		}
	}

}
