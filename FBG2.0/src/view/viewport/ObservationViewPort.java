package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import model.director.GameDirector;

public class ObservationViewPort implements ViewPort {

	
	private static ObservationViewPort port = null;
	private List<String> options;
	private static int xStart;
	private static int yStart;
	private static int padding = 1;
	
	private ObservationViewPort() {
		this.options = new ArrayList<>();
		xStart = (int)(GameDirector.getSize().width * .8);
		yStart = 0;
	}
	
	public static ObservationViewPort getInstance() {
		if(port == null) {
			port = new ObservationViewPort();
		}
		return port;
	}
	
	@Override
	public void draw(Graphics g) {
		
		if(options != null) {
			int height = g.getFontMetrics().getHeight();
			g.setColor(Color.BLACK);
			g.fillRect(xStart, yStart, (int)(GameDirector.getSize().getWidth() * 0.2), (options.size() * (height + padding) + 100));
			g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN,15));
			g.setColor(Color.WHITE);
			for(int i = 0; i < options.size(); i++) {
				height = g.getFontMetrics().getHeight();
				g.drawString(options.get(i), xStart, yStart + (i * (padding + height) + 15));
			}
		}

	}

	public void setStrings(List<String> strings) {
		this.options = strings;
	}

}
