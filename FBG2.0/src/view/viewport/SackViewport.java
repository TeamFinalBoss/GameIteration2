package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.director.AvatarInteractionManager;
import model.director.GameDirector;
import model.entity.Entity;
import model.factories.SpriteFactory;
import model.item.Takeable;
import view.MousePointClick;
import controller.commands.sceneChangers.ArmorySackMaintainer;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.Selectable;

public class SackViewport extends Observable implements ViewPort, Observer, MousePointClick, Selectable {
	
	private int currentSelection;
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
	private static Entity avatar = AvatarInteractionManager.getInstance().getAvatar();

	public SackViewport() {
		width = (int) (GameDirector.getSize().width * 0.8);
		height = (int) (GameDirector.getSize().height * 0.8);
		startX = width - (itemsPerRow * sizeOfBox) - Xpadding;
		startY = height - (maximumNumberOfRows * sizeOfBox) - offset - Ypadding;
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
			List<Takeable> items  = AvatarInteractionManager.getInstance().getSack();
			for(int i = 0; i < maximumNumberOfRows; ++i) {
				for(int j = 0; j < itemsPerRow; ++j) {
					//actually draw item using i, j, items per row and other shit.
					drawItem(g,i,j, items);				
				}
			}
		}
	}
	
	private boolean canDraw() {
		return ArmorySackMaintainer.isPressedSack();
	}



	private void drawItem(Graphics g, int i, int j, List<Takeable> items) {
		if((i * itemsPerRow + j) + (maximumNumberOfRows * currentMinRow) == currentSelection) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.ORANGE);
		}
		g.drawRect((j * sizeOfBox) + startX, (i * sizeOfBox)+ startY, sizeOfBox - 1, sizeOfBox - 1);
		
		if(items.size()  > (i * itemsPerRow + j) + (maximumNumberOfRows * currentMinRow)) {
			int value = (i * itemsPerRow + j) + (maximumNumberOfRows * currentMinRow);
			BufferedImage image = SpriteFactory.hashIDtoImage(items.get(value).id);
			g.drawImage(image,(j * sizeOfBox) + startX,  (i * sizeOfBox)+ startY, null);
		}
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
	public int getActiveLocation(Point point) {
		for(int i = 0; i < maximumNumberOfRows; ++i) {
			for(int j = 0; j < itemsPerRow; ++j) {
				if(withinXBounds(j,(int)point.getX()) && withinYBounds(i,(int)point.getY())) {	
					currentSelection = ((i * itemsPerRow) + j) + (maximumNumberOfRows * currentMinRow);
					setChanged();
					notifyObservers();
					return currentSelection;
				}
			}
		}
		return -1;
	}


	@Override
	public void verifyChange(Point point) {
		if(ArmorySackMaintainer.isPressedArmory()) {
			if(isInArmory(point)) {
				SceneChanger.getInstance().changeScene(SceneType.ARMORY);
			}
		}
		
	}


	private boolean isInArmory(Point point) {
		return isInLine(point) || isInCross(point);
	}


	private boolean isInCross(Point point) {
		int x = (int)point.getX();
		int y = (int)point.getY();
		int startingX = ArmoryViewport.getStartX() - ArmoryViewport.getSizeOfBox();
		int startingY = ArmoryViewport.getStartY() + ArmoryViewport.getSizeOfBox();
		return x >= startingX && x <= startingX + ArmoryViewport.getNumberacross() * ArmoryViewport.getSizeOfBox() &&
				y>= startingY && y <= startingY + ArmoryViewport.getSizeOfBox();
	}


	private boolean isInLine(Point point) {
		int x = (int)point.getX();
		int y = (int)point.getY();
		return x >= ArmoryViewport.getStartX() && x<=  ArmoryViewport.getStartX() + ArmoryViewport.getSizeOfBox() &&
				y >= ArmoryViewport.getStartY() && y <= ArmoryViewport.getNumberdown() * ArmoryViewport.getSizeOfBox() + ArmoryViewport.getStartY();
	}

}
