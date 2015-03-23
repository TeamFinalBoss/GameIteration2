package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import model.director.GameDirector;

public class DialogueViewport implements ViewPort, Observer {

	private String[] options = {"Hi", "Bye", "Fuck you"};
	private static int xStart;
	private static int yStart;
	private static int padding = 1;
	
	
	public DialogueViewport() {
		xStart = (int)(GameDirector.getSize().width * .8);
		yStart = 0;
	}
	
	@Override
	public void draw(Graphics g) {
		if(options != null) {
		int height = g.getFontMetrics().getHeight();
		g.setColor(Color.BLACK);
		g.fillRect(xStart, yStart, (int)(GameDirector.getSize().getWidth() * 0.2), (options.length * (height + padding) + 100));
		g.setFont(new Font(g.getFont().getFamily(), Font.BOLD,15));
		g.setColor(Color.WHITE);
		for(int i = 0; i < options.length; i++) {
			/*
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
			 */
				height = g.getFontMetrics().getHeight();
				g.drawString(options[i], xStart, yStart + (i * (padding + height) + 15));
			}
		}
	

	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
