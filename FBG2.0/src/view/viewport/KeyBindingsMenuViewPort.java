package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class KeyBindingsMenuViewPort extends MainMenuViewPort {

	private final int logoY = 100;
	private final int maximumOptionsDisplayed = 9;
	
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
            for (int i = activeOptionIndex; i < activeOptionIndex + maxIterations; i++) {
                if (i == activeOptionIndex) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.black);
                }
                int stringWidth = g.getFontMetrics().stringWidth(options[i % (options.length)]);
                int stringHeight = g.getFontMetrics().getHeight();
                int padding = 25;
                g.drawString(options[i % (options.length)], (width / 2) - (stringWidth / 2), (i  - activeOptionIndex) * (stringHeight + padding) + logoY + logoHeight + stringHeight + padding);
            }
        }
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
