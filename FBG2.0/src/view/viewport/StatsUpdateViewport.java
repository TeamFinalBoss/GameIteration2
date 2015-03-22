package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import model.director.GameDirector;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.Describeable;
import controller.util.SceneObserver;

public class StatsUpdateViewport implements ViewPort, Observer, SceneObserver {
	
	private static int currentValue;
	private static String[] options;
	private static int xStart;
	private static int yStart = 31;
	private static int padding = 25;
	private SceneType type;
	private static Graphics graphics;
	
	public StatsUpdateViewport() {
		int width = GameDirector.getSize().width;
		xStart = width - (int)(GameDirector.getSize().getWidth() * 0.2);
		SceneChanger.getInstance().registerObserver(this);
	}

	@Override
	public void draw(Graphics g) {
		graphics = g;
		if(type.equals(SceneType.STATS_UPDATING)) {
			if(options != null) {
				g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN, 30));
				int height = g.getFontMetrics().getHeight();
				g.setColor(Color.yellow);
				g.fillRect(xStart, yStart - 31, (int)(GameDirector.getSize().getWidth() * 0.2), options.length * (padding + height));
				for(int i = 0; i < options.length; i++) {
					 if (i == currentValue) {
	                     g.setColor(Color.red);
	                 } else {
	                     g.setColor(Color.black);
	                 }
					g.drawString(options[i], xStart, yStart + i* (padding + height));
				}
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Describeable desc = (Describeable) arg0;
		options = desc.getDescription();
		currentValue = desc.getCurrentIndex();
	}

	@Override
	public void update(SceneType type) {
		this.type = type;
	}

}
