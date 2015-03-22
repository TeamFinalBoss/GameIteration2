package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import model.director.GameDirector;
import view.MousePoint;
import controller.commands.sceneChangers.ArmorySackMaintainer;
import controller.util.Selectable;

public class ArmoryViewport extends Observable implements ViewPort, Observer, MousePoint, Selectable {
	
	private int currentSelection;
	private static int width;
	private static int height;
	private static final int sizeOfBox = 64;
	private static int startX;
	private static int startY = 0;
	private static final int offset = 31;
	private static final int Ypadding = 10;
	
	public ArmoryViewport() {
		currentSelection = 0;
		width = GameDirector.getSize().width;
		height = GameDirector.getSize().height;
		startX = sizeOfBox;
		startY = height - (4 * sizeOfBox) - Ypadding - offset;
	}
	
	@Override
	public void draw(Graphics g) {
		if(canDraw()) {
			for(int i = 0; i < 6; i++) {
				int xCoord;
				int yCoord;
				setColor(g);
				if(currentSelection == i) {
					setActiveColor(g);
				}
				if(i == 2) {
					xCoord = startX - sizeOfBox;
					yCoord = startY + sizeOfBox;
				} else if(i ==3) {
					xCoord = startX + sizeOfBox;
					yCoord = startY + sizeOfBox;
				} else {
					if(i < 2) {
						yCoord = startY + sizeOfBox * i;
					} else {
						yCoord = startY + sizeOfBox * (i-2);
					}
					xCoord = startX;
				}
				g.drawRect(xCoord, yCoord, sizeOfBox - 1, sizeOfBox - 1);
			}
		}

	}

	private boolean canDraw() {
		return ArmorySackMaintainer.isPressedArmory();
	}

	private void setActiveColor(Graphics g) {
		g.setColor(Color.GREEN);
		
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
	public void getActiveLocation(Point point) {
		for(int i = 0; i < 6; i++) {
			if(withinXBounds(i,(int)point.getX()) && withinYBounds(i,(int)point.getY())){
				currentSelection = i;
				setChanged();
				notifyObservers();
			}
		}
		
	}

	private boolean withinYBounds(int i, int y) {
		if(i == 2 || i == 3) {
			return y >= startY + sizeOfBox + offset && y <= startY + 2*sizeOfBox + offset;
		} else {
			if(i < 2) {
				return y >= startY + sizeOfBox * i + offset && y <= startY + sizeOfBox * i + sizeOfBox + offset;
			} else {
				return y >= startY + sizeOfBox * (i-2) + offset && y <= startY + sizeOfBox * (i-2) + sizeOfBox + offset;
			}
		}
	}

	private boolean withinXBounds(int i, int x) {
		if(i ==2) {
			return x >= startX - sizeOfBox && x <= startX;
		} else if(i == 3) {
			return x >= startX + sizeOfBox && x <= startX + 2*sizeOfBox; 
		} else {
			return x >= startX && x <= startX + sizeOfBox;
		}
	}

	@Override
	public int getCurrentIndex() {
		return this.currentSelection;
	}



}
