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
	private static int currentMaxRow = 4;
	private static int currentMinRow = 0;
	private static int maximumNumberOfRows = 5;
	private static final int sizeOfBox = 63;
	private static final int startX = 0;
	private static final int startY = 0;
	private Graphics graph;

	public SackViewport() {

	}

	

	@Override
	public void draw(Graphics g) {
		graph = g;
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
		g.drawRect((j * sizeOfBox) + startX, (i * sizeOfBox)+ startY, sizeOfBox - 1, sizeOfBox - 1);
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
		int heightUpperBounds = (i*sizeOfBox) + i + startY;
		int heightLowerBounds = heightUpperBounds + sizeOfBox;
		System.out.println(heightUpperBounds + " " + heightLowerBounds + " " + y);
		return ((y <= heightLowerBounds) && (y >= heightUpperBounds));
	}

	protected boolean withinXBounds(int j, int x) {
		int widthLeftBounds = (j * sizeOfBox) + j + startX;
		int widthRightBounds = widthLeftBounds + sizeOfBox;

		return ((x >= widthLeftBounds) && (x <= widthRightBounds));
	}



	@Override
	public void getActiveLocation(Point point) {
		for(int i = 0; i < maximumNumberOfRows; ++i) {
			for(int j = 0; j < itemsPerRow; ++j) {
				graph.setColor(Color.BLACK);
				graph.drawRect((j * sizeOfBox) + startX,(i*sizeOfBox)  + startY, sizeOfBox - 1, sizeOfBox - 1);
				if(withinXBounds(j,(int)point.getX()) && withinYBounds(i,(int)point.getY())) {	
					
					currentSelection = ((i * itemsPerRow) + j) + (maximumNumberOfRows * currentMinRow);
					setChanged();
					notifyObservers();
				}
			}
		}
	}

}
