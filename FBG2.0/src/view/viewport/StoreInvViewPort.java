package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.SceneObserver;
import controller.util.Selectable;
import model.director.AvatarInteractionManager;
import model.director.GameDirector;
import model.factories.SpriteFactory;
import model.item.Takeable;

public class StoreInvViewPort implements ViewPort, Observer, SceneObserver {
	
	
	private int currentSelection;
	private SceneType currentType;
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
	
	public StoreInvViewPort() {
		width = (int) (GameDirector.getSize().width * 0.4);
		height = (int) (GameDirector.getSize().height * 0.4) + (maximumNumberOfRows * sizeOfBox) + 15;
		startX = width - (itemsPerRow * sizeOfBox) - Xpadding;
		startY = height - (maximumNumberOfRows * sizeOfBox) - offset - Ypadding;
		SceneChanger.getInstance().registerObserver(this);
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
		return (currentType.equals(SceneType.STORE) || currentType.equals(SceneType.STORE_INV));
	}
	
	private void drawItem(Graphics g, int i, int j, List<Takeable> items) {
		if((i * itemsPerRow + j) + (maximumNumberOfRows * currentMinRow) == currentSelection) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.white);
		}
		g.fillRect((j * sizeOfBox) + startX, (i * sizeOfBox)+ startY, sizeOfBox - 1, sizeOfBox - 1);
		
		if(items.size()  > (i * itemsPerRow + j) + (maximumNumberOfRows * currentMinRow)) {
			int value = (i * itemsPerRow + j) + (maximumNumberOfRows * currentMinRow);
			BufferedImage image = SpriteFactory.hashIDtoImage(items.get(value).id);
			g.drawImage(image,(j * sizeOfBox) + startX,  (i * sizeOfBox)+ startY, null);
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
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
		currentType = type;
	}

}
