package view.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.mouse.MouseParser;
import model.director.GameDirector;

/**
 * This sets up the GUI window for the game, it holds an image in its content
 * pane.
 *
 * @see JFrame
 * @author ChrisMoscoso
 */
public class GameWindow {

    private JFrame frame;
    private ImagePanel panel;

    //private final int OFFSET = 10; //The offset created by the window/menu bar

    /**
     * Initializes the window with default size of 832 by 640 and adds a
     * {@link GamePanel}
     */
    public GameWindow() {
        this(1, 1);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        
        frame.dispose();
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    /**
     * A constructor that allows you to specify the size
     *
     * @param width of the window in pixels
     * @param height of the window in pixels
     */
    public GameWindow(int width, int height) {
        frame = new JFrame();
        
        frame.setSize(new Dimension(width, height));
        frame.setTitle("FINAL BOSS 2.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.setVisible(true);
        frame.addComponentListener(new GameWindowComponentListener());


        //Set up the panel for drawing
        panel = new ImagePanel();
        frame.setContentPane(panel);
        frame.validate();
    }
    
    /**
     * Returns the size of the window.
     *
     * @return the size of the window
     */
    public Dimension getSize() {
        return frame.getSize();
    }

    /**
     * Take the buffered Image and paint it to the screen
     *
     * @param bufferedImage the image that should be drawn to the screen
     */
    public void paintImageToScreen(BufferedImage bufferedImage) {
        panel.setImage(bufferedImage);
        panel.repaint();
    }

    /**
     * Adds a key controller to the windows main panel
     *
     * @param k the key listener to be added
     */
    public void addKeyController(KeyListener k) {
        frame.addKeyListener(k);
    }

    /**
     * Adds a mouse controller to the windows main panel
     *
     * @param mouseParser the mouse controller to be added
     */
    public void addMouseController(MouseAdapter mouseParser) {
        frame.addMouseListener(mouseParser);
        frame.addMouseMotionListener(mouseParser);
        frame.addMouseWheelListener(mouseParser);
    }

    /**
     * Adds a mouse motion controller to the windows main panel
     *
     * @param m the mouse motion listener to be added
     */
    public void addMouseMotionController(MouseMotionListener m) {
        frame.addMouseMotionListener(m);
    }
    
    public void close() {
    	frame.setVisible(false);
    	frame.dispose();
    }

    /**
     * A JPanel that is comprised entirely of a bufferedImage
     *
     * @author ChrisMoscoso
     */
    private class ImagePanel extends JPanel {

        private BufferedImage image;

        public ImagePanel(){
            this.setBackground(Color.WHITE);
        }
        
        /**
         * Set the image for the ImagePanel
         *
         * @param image the main image of the ImagePanel
         */
        public void setImage(BufferedImage image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    
    private class GameWindowListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
            JFrame f = new JFrame();
            f.addWindowStateListener(null);
            f.addWindowStateListener(null);
        }

        @Override
        public void windowClosing(WindowEvent e) {}

        @Override
        public void windowClosed(WindowEvent e) {}

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}
        
    }
    
    private class GameWindowComponentListener implements ComponentListener{

        @Override
        public void componentResized(ComponentEvent e) {
            GameDirector.setSize(GameWindow.this.frame.getWidth(), GameWindow.this.frame.getHeight());
        }

        @Override
        public void componentMoved(ComponentEvent e) {}

        @Override
        public void componentShown(ComponentEvent e) {}

        @Override
        public void componentHidden(ComponentEvent e) {}
       
    }

	public void removeKeyController(KeyListener listener) {
		frame.removeKeyListener(listener);
	}

	public void removeMouseController(MouseParser mouse) {
		frame.removeMouseListener(mouse);
		frame.removeMouseMotionListener(mouse);
		frame.removeMouseWheelListener(mouse);
	}
    
}
