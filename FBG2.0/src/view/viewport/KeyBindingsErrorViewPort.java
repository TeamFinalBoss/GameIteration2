package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import controller.util.Errorable;
import model.director.GameDirector;

public class KeyBindingsErrorViewPort implements ViewPort, Observer {

	private int width;
	private final String defaultString = "Press Confirm to remap an option, \nnavigate to save to save your options, \n"
									+ " or navigate to cancel to discard the changes.";
	
	private String errorString = null;
	
	
	public KeyBindingsErrorViewPort() {
		
	}
	
	
	
	@Override
	public void draw(Graphics g) {
		
		 if (GameDirector.getSize() != null) {
	            width = GameDirector.getSize().width;
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

	@Override
	public void update(Observable o, Object arg) {
		Errorable error = (Errorable) o;
		this.errorString = error.getErrorString();
	}

}
