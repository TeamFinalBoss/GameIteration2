package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import model.director.GameDirector;
import controller.util.Describeable;

public class StatsUpdateViewport implements ViewPort, Observer{
	
	private static int currentValue;
	private static String[] options;
	private static int xStart;
	private static int screenWidth;
	private static int yStart;
	private static int height = 16;
	private static int padding = 1;
	
	public StatsUpdateViewport() {
		screenWidth = GameDirector.getSize().width;
		xStart = screenWidth - (int)(GameDirector.getSize().getWidth() * 0.2);
		yStart = 0;
	}

	@Override
	public void draw(Graphics g) {
		if(options != null) {
			
			int height = g.getFontMetrics().getHeight();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(xStart, yStart-height - padding, (int)(GameDirector.getSize().getWidth() * 0.2), (options.length ) * (padding + height));
			for(int i = 0; i < options.length; i++) {
				g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN, 15));
				 if (i== currentValue) {
					 g.setColor(Color.red);
	             } else {
	                 g.setColor(Color.black);
	             }
				 if(i == 13) {
					 g.setFont(new Font(g.getFont().getFamily(), Font.BOLD, 15));
				 }
				 String[] strings =options[i].split("\t");
				 if(strings.length == 2) {
					 if(i == 0) {
						 g.setFont(new Font(g.getFont().getFamily(), Font.BOLD,15));
					 } 
					 g.drawString(strings[0], xStart, yStart + i* (padding+height));
					 
					 g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN, 15));
					 int width = g.getFontMetrics().stringWidth(strings[1]);
					 g.drawString(strings[1], screenWidth- width - 5, yStart + i * (padding+height));
				 } else {
					 g.drawString(options[i], xStart, yStart + i* (padding + height));
				 }
				 
				}
			}
	
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Describeable desc = (Describeable) arg0;
		options = desc.getDescription();
		yStart = GameDirector.getSize().height - (options.length * (height + padding));
		currentValue = desc.getCurrentIndex() + 1;
	}
}
