package controller.commands.keyBindings;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.Controller;
import controller.commands.ForwardingCommand;
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsOption;
import controller.keyBindings.KeyBindingsUpdate;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.Describeable;
import controller.util.Errorable;



/**
 * @author Kyle Kyrazis
 * 
 * This class should be used for performing the mapping between old key and new key
 *
 */
public class BindingsUpdate extends Observable implements ForwardingCommand, Observer, Errorable   {

	private KeyBindings currentBindings;
	private KeyBindingsUpdate bindingsUpdate;
	private KeyBindingsOption currentSelection;

	private SceneChanger changer = SceneChanger.getInstance();
	private String delimeter = " ";
	private String errorString;
	
	public BindingsUpdate() {
		currentBindings = new KeyBindings();
		bindingsUpdate = new KeyBindingsUpdate();
	}
	
	public BindingsUpdate(KeyBindings currentBindings, KeyBindingsUpdate bindingsUpdate) {
		this.currentBindings = currentBindings;
		this.bindingsUpdate = bindingsUpdate;
	}
	
	
	
	public void execute(Integer value) {
		Map<KeyBindingsOption, Integer> currentMapping = currentBindings.getBindingsReverse();
		try {
			bindingsUpdate.addUpdate(currentMapping.get(currentSelection), value);
			errorString = null;
			changer.changeScene(SceneType.KEY_BINDINGS);
		} catch(IllegalArgumentException e) {
			errorString = e.getMessage() + " Please try again.";
		}
		setChanged();
		notifyObservers();
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

	public void clear() {
		this.bindingsUpdate.clear();
		setChanged();
		notifyObservers();
	}

	public void register() {
		Controller.getInstance().addObserver(this, SceneType.KEY_BINDINGS);
	}
	
	public void addObserver(Observer o) {
		super.addObserver(o);
        setChanged();
        notifyObservers();
	}

	@Override
	public String getErrorString() {
		return this.errorString;
	}

}
