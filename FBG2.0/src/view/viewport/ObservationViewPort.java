package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import controller.util.Describeable;
import model.director.GameDirector;

public class ObservationViewPort implements ViewPort, Observer {

	
	private static ObservationViewPort port = null;
	private String[] options;
	private static int xStart;
	private static int yStart;
	private static int padding = 1;
	
	public ObservationViewPort() {
		xStart = (int)(GameDirector.getSize().width * .8);
		yStart = 0;
	}
	
	@Override
	public void draw(Graphics g) {
		
		if(options != null) {
			int height = g.getFontMetrics().getHeight();
			g.setColor(Color.BLACK);
			g.fillRect(xStart, yStart, (int)(GameDirector.getSize().getWidth() * 0.2), (options.length* (height + padding) + 100));
			g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN,15));
			g.setColor(Color.WHITE);
			for(int i = 0; i < options.length; i++) {
				height = g.getFontMetrics().getHeight();
				g.drawString(options[i], xStart, yStart + (i * (padding + height) + 15));
			}
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		Describeable des = (Describeable)o;
		this.options = des.getDescription();
	}

}
