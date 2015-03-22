package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;

import view.MousePoint;
import controller.util.Describeable;

public class KeyBindingsMenuViewPort extends MainMenuViewPort implements MousePoint {

	private final int logoY = 100;
	private final int maximumOptionsDisplayed = 9;
	private int minimumRow = 0;
	private int maximumRow = minimumRow + maximumOptionsDisplayed;
	
	public KeyBindingsMenuViewPort() {
		
	}
	
	
	
	protected void drawMenu(Graphics g) {
		String[] options = super.getOptions();
		int activeOptionIndex = super.currentSelectionIndex();
		int width = super.getWidth();
		int logoHeight = super.getLogoHeight();
		
		int maxIterations = options.length < maximumOptionsDisplayed ? options.length : maximumOptionsDisplayed;
		
		g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN, 30));
		try {
        if (options != null) {
            for (int i = minimumRow; i < minimumRow + maxIterations; i++) {
                if (i == activeOptionIndex) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.black);
                }
                int stringWidth = g.getFontMetrics().stringWidth(options[i]);
                int stringHeight = g.getFontMetrics().getHeight();
                int padding = 25;
                g.drawString(options[i], (width / 2) - (stringWidth / 2), (i - minimumRow ) * (stringHeight + padding) + logoY + logoHeight + stringHeight + padding);
            }
        }
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
    public void update(Observable o, Object arg) {
		super.update(o, arg);
        int currentIndex = super.currentSelectionIndex();
        if(currentIndex >= maximumRow) {
        	++maximumRow;
        	++minimumRow;
        } else if(currentIndex < minimumRow) {
        	--maximumRow;
        	--minimumRow;
        }
    }

	
	
}
