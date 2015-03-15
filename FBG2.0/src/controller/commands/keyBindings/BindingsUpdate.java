package controller.commands.keyBindings;

import java.util.List;

import controller.commands.Commandable;
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsUpdate;
import controller.util.Describeable;

/**
 * @author Kyle Kyrazis
 * 
 * This class should be used for performing the mapping between old key and new key
 *
 */
public class BindingsUpdate implements Commandable, Describeable {

	private KeyBindings currentBindings;
	private KeyBindingsUpdate bindingsUpdate;
	
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
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected KeyBindingsUpdate getKeyBindingsUpdate() {
		return this.bindingsUpdate;
	}
	

}
