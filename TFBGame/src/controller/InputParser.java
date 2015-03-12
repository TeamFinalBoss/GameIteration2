package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Kyle Kyrazis
 *
 */
public class InputParser implements KeyListener {
	
	private KeyDispatcher keyDispatcher;
	
	public InputParser() {
		this.keyDispatcher = new KeyDispatcher();
	}
	
	public InputParser(KeyDispatcher keyDispatcher) {
		this.keyDispatcher = keyDispatcher;
	}
	
	
	@Override
	/**
	 * One Key Press forward the key to the KeyDispatcher. Currently converting
	 * the KeyEvent to a lower case character and then forwarding that. If we need
	 * to detect multiple key presses change this to a Set<Character> that gets 
	 * forwarded.
	 * 
	 * @param KeyEvent arg0
	 */
	public void keyPressed(KeyEvent arg0) {
		keyDispatcher.useKey(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
