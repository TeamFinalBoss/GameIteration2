package view;

import javax.swing.JFrame;

/**
 * This sets up the GUI window for the application.
 * @see JFrame
 * @author ChrisMoscoso
 */
public class GameWindow extends JFrame {
    
    /**
     * Initializes the window with default size of 832 by 640 and adds a {@link GamePanel}
     */
    public GameWindow(){
        this(832, 640);
    }

    /**
     * A constructor that allows you to specify the size
     * @param width of the window in pixels
     * @param height of the window in pixels 
     */
    public GameWindow(int width, int height) {
        GamePanel gp = new GamePanel();
        this.getContentPane().add(gp);
    }
}
