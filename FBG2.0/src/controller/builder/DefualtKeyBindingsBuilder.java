package controller.builder;

import java.awt.event.KeyEvent;

import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsOption;

public class DefualtKeyBindingsBuilder {

	public static KeyBindings buildDefaultKeyBindings() {
		KeyBindings bindings = new KeyBindings();
		
		bindings.addBinding(KeyEvent.VK_Z, KeyBindingsOption.DOWN_LEFT);
		bindings.addBinding(KeyEvent.VK_X, KeyBindingsOption.DOWN);
		bindings.addBinding(KeyEvent.VK_C, KeyBindingsOption.DOWN_RIGHT);
		bindings.addBinding(KeyEvent.VK_D, KeyBindingsOption.RIGHT);
		bindings.addBinding(KeyEvent.VK_E, KeyBindingsOption.UP_RIGHT);
		bindings.addBinding(KeyEvent.VK_W, KeyBindingsOption.UP);
		bindings.addBinding(KeyEvent.VK_Q, KeyBindingsOption.UP_LEFT);
		bindings.addBinding(KeyEvent.VK_A, KeyBindingsOption.LEFT);
		bindings.addBinding(KeyEvent.VK_ENTER, KeyBindingsOption.CONFIRM);
		bindings.addBinding(KeyEvent.VK_SHIFT, KeyBindingsOption.DIALOGUE);
		bindings.addBinding(KeyEvent.VK_S, KeyBindingsOption.DROP);
		bindings.addBinding(KeyEvent.VK_I, KeyBindingsOption.SACK);
		bindings.addBinding(KeyEvent.VK_B, KeyBindingsOption.ARMORY);
		bindings.addBinding(KeyEvent.VK_TAB, KeyBindingsOption.NEAREST_ENTITY);
		bindings.addBinding(KeyEvent.VK_ESCAPE, KeyBindingsOption.PAUSE);
		bindings.addBinding(KeyEvent.VK_1, KeyBindingsOption.SKILL_1);
		bindings.addBinding(KeyEvent.VK_2, KeyBindingsOption.SKILL_2);
		bindings.addBinding(KeyEvent.VK_3, KeyBindingsOption.SKILL_3);
		bindings.addBinding(KeyEvent.VK_4, KeyBindingsOption.SKILL_4);
		bindings.addBinding(KeyEvent.VK_5, KeyBindingsOption.SKILL_5);
		bindings.addBinding(KeyEvent.VK_6, KeyBindingsOption.SKILL_6);
		bindings.addBinding(KeyEvent.VK_7, KeyBindingsOption.SKILL_7);
		bindings.addBinding(KeyEvent.VK_8, KeyBindingsOption.SKILL_8);
		bindings.addBinding(KeyEvent.VK_9, KeyBindingsOption.SKILL_9);
		bindings.addBinding(KeyEvent.VK_0, KeyBindingsOption.SKILL_0);
		bindings.addBinding(KeyEvent.VK_T, KeyBindingsOption.TILE_INFO);
		bindings.addBinding(KeyEvent.VK_U, KeyBindingsOption.STATS_UPDATE);
		
		return bindings;
	}

}
