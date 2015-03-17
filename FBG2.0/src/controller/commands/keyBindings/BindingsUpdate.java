package controller.commands.keyBindings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import view.window.GameWindow;
import controller.Controller;
import controller.commands.Commandable;
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsOption;
import controller.keyBindings.KeyBindingsUpdate;
import controller.sceneControllers.SceneType;
import controller.util.Describeable;



/**
 * @author Kyle Kyrazis
 * 
 * This class should be used for performing the mapping between old key and new key
 *
 */
public class BindingsUpdate implements Commandable, Observer, KeyListener   {

	private KeyBindings currentBindings;
	private KeyBindingsUpdate bindingsUpdate;
	private KeyBindingsOption currentSelection;
	private GameWindow window;
	private String delimeter = " ";
	
	public BindingsUpdate() {
		currentBindings = new KeyBindings();
		bindingsUpdate = new KeyBindingsUpdate();
	}
	
	public BindingsUpdate(KeyBindings currentBindings, KeyBindingsUpdate bindingsUpdate) {
		this.currentBindings = currentBindings;
		this.bindingsUpdate = bindingsUpdate;
	}
	
	@Override
	public void execute() {
		window = new GameWindow(100,100);
		window.addKeyController(this);
	}

	protected KeyBindingsUpdate getKeyBindingsUpdate() {
		return this.bindingsUpdate;
	}
	//TODO please right a much better function. This is hideous. Extracts the Enum from the string.
	@Override
	public void update(Observable arg0, Object arg1) {
		Describeable describe = (Describeable) arg0;
		
		String[] currentStrings = describe.getDescription();
		String testString = currentStrings[describe.getCurrentIndex()];
		String[] splitStrings = testString.split(delimeter);
		String finalString = splitStrings[0];
		
		for(int i = 1; i < splitStrings.length -1; i++) {
			finalString += " ";
			finalString += splitStrings[i];
		}
		this.currentSelection =
				KeyBindingsOption.fromString(finalString);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		Map<KeyBindingsOption, Integer> currentMapping = currentBindings.getBindingsReverse();
		boolean isValid = false;
		try {
			bindingsUpdate.addUpdate(currentMapping.get(currentSelection), arg0.getKeyCode());
			isValid = true;
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if(isValid) {
			window.close();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void clear() {
		this.bindingsUpdate.clear();
	}

	public void register() {
		Controller.getInstance().addObserver(this, SceneType.KEY_BINDINGS);
	}

	
	

}
