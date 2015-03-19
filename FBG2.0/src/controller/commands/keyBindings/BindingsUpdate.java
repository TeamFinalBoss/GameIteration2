package controller.commands.keyBindings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import view.viewport.KeyBindingsErrorViewPort;
import model.director.GameDirector;
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
public class BindingsUpdate extends Observable implements Commandable, Observer, KeyListener   {

	private KeyBindings currentBindings;
	private KeyBindingsUpdate bindingsUpdate;
	private KeyBindingsOption currentSelection;
	private GameDirector director = GameDirector.getGameDirector();
	private Controller controller = Controller.getInstance();
	private KeyBindingsErrorViewPort port = KeyBindingsErrorViewPort.getInstance();
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
		director.removeKeyListener(controller.getActiveListener());
		director.addKeyListener(this);
		port.setErrorString("Updating");
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
		try {
			bindingsUpdate.addUpdate(currentMapping.get(currentSelection), arg0.getKeyCode());
			director.removeKeyListener(this);
			director.addKeyListener(controller.getActiveListener());
			port.reset();
			setChanged();
			notifyObservers();
		} catch(IllegalArgumentException e) {
			port.setErrorString(e.getMessage() + " Please try again");
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
	
	public void addObserver(Observer o) {
		super.addObserver(o);
        setChanged();
        notifyObservers();
	}

}
