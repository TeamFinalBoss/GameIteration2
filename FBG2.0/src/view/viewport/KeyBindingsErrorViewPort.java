package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import model.director.GameDirector;

public class KeyBindingsErrorViewPort implements ViewPort {

	private int width;
	private int height;
	private final String defaultString = "Press Confirm to remap an option, \nnavigate to save to save your options, \n"
									+ " or navigate to cancel to discard the changes.";
	
	private String errorString = null;
	
	private static KeyBindingsErrorViewPort port = null;
	
	private KeyBindingsErrorViewPort() {
		
	}
	
	public static KeyBindingsErrorViewPort getInstance() {
		if(port == null) {
			port = new KeyBindingsErrorViewPort();
		}
		return port;
	}
	
	@Override
	public void draw(Graphics g) {
		
		 if (GameDirector.getSize() != null) {
	            width = GameDirector.getSize().width;
	            height = GameDirector.getSize().height;
	    }
		
		g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN, 30));
		
		String printString = (errorString == null) ? defaultString : errorString;
		int stringWidth = g.getFontMetrics().stringWidth(printString);
		g.setColor(errorString == null ? Color.blue : Color.red);
		g.drawString(printString, (width / 2) - (stringWidth / 2), 80);   

		
		
		 
	}
	
	public void setErrorString(String str) {
		this.errorString = str;
	}
	public void reset() {
		errorString = null;
	}

}
