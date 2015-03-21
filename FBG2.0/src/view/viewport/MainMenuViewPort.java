package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import view.MousePoint;
import model.director.GameDirector;
import controller.util.Describeable;
import controller.util.Selectable;

/**
 * The MainMenuViewPort draws the main menu
 *
 * @author ChrisMoscoso
 */
public class MainMenuViewPort extends Observable implements ViewPort, Observer, MousePoint, Selectable {

    private String[] options;
    private int activeOptionIndex;
    private int width, height;
    private int logoHeight;
    private int padding = 25;
    private Graphics graphics;
    private final int logoY = 100;
    private int[] stringWidth;
    private int stringHeight;

    
    public MainMenuViewPort(){
        
    }

    @Override
    public void draw(Graphics g) {
        if (GameDirector.getSize() != null) {
            width = GameDirector.getSize().width;
            height = GameDirector.getSize().height;
        }
        

        /*DRAW BG*/
        ImageIcon imageIcon = new ImageIcon("src/resources/img/bg.gif");
        Image img = imageIcon.getImage();
        g.drawImage(img, 0, 0, width, height, null);
        

        /*DRAW LOGO*/
        BufferedImage fbLogo;
        logoHeight = 0;
        
       try {
            fbLogo = ImageIO.read(new File("src/resources/img/FinalBoss.png"));
            logoHeight = fbLogo.getHeight();
            
            int logoX = width / 2 - fbLogo.getWidth() / 2;
            g.drawImage(fbLogo, logoX, logoY, null);
        } catch (IOException ex) {
        }
        
        
        drawMenu(g);/*DRAW MENU*/
       
    }
    
    protected void drawMenu(Graphics g) {
    	 g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN, 30));
    	 graphics = g;
         if (options != null) {
        	 stringWidth = new int[options.length];
             for (int i = 0; i < options.length; i++) {
                 if (i == activeOptionIndex) {
                     g.setColor(Color.red);
                 } else {
                     g.setColor(Color.black);
                 }
                 stringWidth[i] = g.getFontMetrics().stringWidth(options[i]);
                 stringHeight = g.getFontMetrics().getHeight();
                 g.drawString(options[i], (width / 2) - (stringWidth[i] / 2), i * (stringHeight + padding) + logoY + logoHeight + stringHeight + padding);
             }
         }
    }

    @Override
    public void update(Observable o, Object arg) {
        Describeable m = (Describeable) o;
        options = m.getDescription();
        activeOptionIndex = m.getCurrentIndex();
    }
    
    protected String[] getOptions() {
    	return this.options;
    }
    
    protected int currentSelectionIndex() {
    	return this.activeOptionIndex;
    }
    
    protected int getWidth() {
    	return this.width;
    }
    protected int getHeight() {
    	return this.height;
    }
    
    protected int getLogoHeight() {
    	return this.logoHeight;
    }

	@Override
	public void getActiveLocation(Point point) {
		if (options != null) {
			int checkWidth;
			int checkHeight;
			for (int i = 0; i < options.length; i++) {
				//graphics.setColor(Color.BLUE);
				//if(i > 1) {
				//	graphics.setColor(Color.GREEN);
				//}
				//graphics.setFont(new Font(graphics.getFont().getFamily(), Font.PLAIN, 30));
				//checkWidth = graphics.getFontMetrics().stringWidth(options[i]);
				//checkHeight = graphics.getFontMetrics().getHeight();
          
				graphics.drawRect((width/2 - stringWidth[i]/2),(i * (stringHeight + padding) + logoY + logoHeight + stringHeight + padding) - stringHeight, stringWidth[i], stringHeight);
				System.out.println("X " + point.getX() + " Y" + point.getY());
				if(withinYBounds(stringHeight, i, point) && withinXBounds(stringWidth[i],i,point)) {
					activeOptionIndex = i;
					setChanged();
					notifyObservers();
				}
			}
		}
	}

	private boolean withinYBounds(int checkHeight, int i, Point point) {
		int heightLowerBounds = (i * (checkHeight + padding) + logoY + logoHeight + checkHeight + padding);
		int heightUpperBounds = heightLowerBounds - checkHeight;
		
		//System.out.println("Lower" + heightLowerBounds + " Higher" + heightUpperBounds);

		return (((int)point.getY() <= heightLowerBounds) && ((int)point.getY() >= heightUpperBounds));
	}

	private boolean withinXBounds(int checkWidth, int i, Point point) {
		int widthLeftBounds = width/2 - checkWidth/2;
		int widthRightBounds = width/2 + checkWidth/2;
		
		//System.out.println("Left" + widthLeftBounds + " Right" + widthRightBounds);
	
		return (((int)point.getX() >= widthLeftBounds) && ((int)point.getX() <= widthRightBounds));
	}

	@Override
	public int getCurrentIndex() {
		return this.activeOptionIndex;
	}
    
}
