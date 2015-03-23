package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import model.director.GameDirector;

import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.SceneObserver;
import controller.util.Selectable;

public class StoreFrontViewPort implements ViewPort, Observer, SceneObserver {

	private int currentSelection;
	private SceneType type;
	private static final int itemsPerRow = 5;
	private static int currentMaxRow = 4;
	private static int currentMinRow = 0;
	private static int maximumNumberOfRows = 5;
	private static final int sizeOfBox = 64;
	private static int startX;
	private static int startY = 0;
	private static final int offset = 0;
	private static final int Ypadding = 0;//10;
	private static final int Xpadding = 0;//17;
	private static int height;
	private static int width;

	public StoreFrontViewPort() {
		width = (int) (GameDirector.getSize().width * 0.4);
		height = (int) (GameDirector.getSize().height * 0.4);
		startX = width - (itemsPerRow * sizeOfBox) - Xpadding;
		startY = height - (maximumNumberOfRows * sizeOfBox) - offset - Ypadding;
		SceneChanger.getInstance().registerObserver(this);
	}

	
	public static int getMaximumNumberOfRows() {
		return maximumNumberOfRows;
	}
	
	public static int getItemsPerRow() {
		return itemsPerRow;
	}
	
	public static int getSizeOfBox() {
		return sizeOfBox;
	}
	
	public static int getStartX() {
		return startX;
	}
	
	public static int getStartY() {
		return startY;
	}
	
	public static int getYPadding() {
		return Ypadding;
	}
	
	public static int getXPadding() {
		return Xpadding;
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
		return type.equals(SceneType.STORE);
	}



	private void drawItem(Graphics g, int i, int j) {
		if((i * itemsPerRow + j) + (maximumNumberOfRows * currentMinRow) == currentSelection) {
			g.setColor(Color.GREEN);
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
	
	protected boolean withinYBounds(int i, int y) {
		int heightUpperBounds = (i*sizeOfBox) + i + startY + offset;
		int heightLowerBounds = heightUpperBounds + sizeOfBox;
		return ((y <= heightLowerBounds) && (y >= heightUpperBounds));
	}

	protected boolean withinXBounds(int j, int x) {
		int widthLeftBounds = (j * sizeOfBox) + j + startX;
		int widthRightBounds = widthLeftBounds + sizeOfBox;

		return ((x >= widthLeftBounds) && (x <= widthRightBounds));
	}


	@Override
	public void update(SceneType type) {
		this.type = type;
	}


}
