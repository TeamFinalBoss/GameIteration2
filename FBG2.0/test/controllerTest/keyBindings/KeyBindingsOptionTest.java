package controllerTest.keyBindings;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.keyBindings.KeyBindingsOption;

public class KeyBindingsOptionTest {
	
	private KeyBindingsOption option;
	
	@Before
	public void setUp() {
		option = KeyBindingsOption.DOWN;
	}
	
	@Test
	public void testToString() {
		assertEquals("Down", option.toString());
	}
	
	@Test
	public void testFromString() {
		assertEquals(KeyBindingsOption.NEAREST_ENTITY, KeyBindingsOption.fromString("Target Nearest Entity"));
	}

}
