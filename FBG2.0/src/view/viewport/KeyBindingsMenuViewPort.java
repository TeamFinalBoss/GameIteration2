package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import view.MousePoint;
import controller.util.Describeable;
import controller.util.Selectable;

public class KeyBindingsMenuViewPort extends MainMenuViewPort implements ViewPort,MousePoint,Observer,Selectable {

	private final int logoY = 100;
	private final int maximumOptionsDisplayed = 9;
	private int minimumRow = 0;
	private int maximumRow = minimumRow + maximumOptionsDisplayed;
	private int stringHeight = 39;
	
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
        	stringWidth = new int[options.length];
            for (int i = minimumRow; i < minimumRow + maxIterations; i++) {
                if (i == activeOptionIndex) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.black);
                }
                stringWidth[i] = g.getFontMetrics().stringWidth(options[i]);
                stringHeight = g.getFontMetrics().getHeight();
                int padding = 25;
                g.drawString(options[i], (width / 2) - (stringWidth[i]/ 2), (i - minimumRow ) * (stringHeight + padding) + logoY + logoHeight + stringHeight + padding);
            }
        }
		} catch(Exception e) {
		}
	}
	
	public int getActiveLocation(Point point) {
		if (super.getOptions() != null) {
			int maxIterations = super.getOptions().length < maximumOptionsDisplayed ? super.getOptions().length : maximumOptionsDisplayed;
			for (int i = minimumRow; i < minimumRow + maxIterations; i++) {
				if(withinYBounds(stringHeight, i - minimumRow, (int)point.getY()) && withinXBounds(stringWidth[i],i-minimumRow,(int)point.getX())) {
					super.setActiveIndex(i);
					setChanged();
					notifyObservers();
					return i;
				}
			}
		}
		return -1;
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
