package controllerTest.keyBindings;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsOption;
import controller.keyBindings.KeyBindingsUpdate;

public class KeyBindingsTest {

	private KeyBindings keyBindings;
	
	@Before
	public void setUp() throws Exception {
		Map<Integer, KeyBindingsOption> map = new HashMap<Integer, KeyBindingsOption>();
		map.put(50, KeyBindingsOption.CANCEL);
		map.put(51, KeyBindingsOption.CONFIRM);
		map.put(52, KeyBindingsOption.DIALOGUE);
		
		keyBindings = new KeyBindings(map);
	}

	@Test
	public void testUpdateBindings() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(2, 51);
		map.put(4, 52);
		map.put(6, 50);
		
		KeyBindingsUpdate update = new KeyBindingsUpdate(map);
		
		KeyBindings newBindings = keyBindings.updateBindings(update);
		
		Map<Integer, KeyBindingsOption> newMap = newBindings.getBindings();
		assertEquals(KeyBindingsOption.DIALOGUE, newMap.get(4));
		assertEquals(KeyBindingsOption.CONFIRM, newMap.get(2));
		assertEquals(KeyBindingsOption.CANCEL, newMap.get(6));
	}
	
	@Test
	public void testUpdateBindingsWithBogusUpdate() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(2, 51);
		map.put(4, 52);
		map.put(6, 50);
		map.put(10, 3);
		
		
		KeyBindingsUpdate update = new KeyBindingsUpdate(map);
		
		KeyBindings newBindings = keyBindings.updateBindings(update);
		
		Map<Integer, KeyBindingsOption> newMap = newBindings.getBindings();
		assertEquals(KeyBindingsOption.DIALOGUE, newMap.get(4));
		assertEquals(KeyBindingsOption.CONFIRM, newMap.get(2));
		assertEquals(KeyBindingsOption.CANCEL, newMap.get(6));
		assertEquals(3,newMap.size());
	}
	
	@Test
	public void testUpdateBindingsWithDifferentValues() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(100, 51);
		map.put(50, 52);
		map.put(97, 50);		
		
		KeyBindingsUpdate update = new KeyBindingsUpdate(map);
		
		KeyBindings newBindings = keyBindings.updateBindings(update);
		
		Map<Integer, KeyBindingsOption> newMap = newBindings.getBindings();
		assertEquals(KeyBindingsOption.CONFIRM, newMap.get(100));
		assertEquals(KeyBindingsOption.CANCEL, newMap.get(97));
		assertEquals(KeyBindingsOption.DIALOGUE, newMap.get(50));
		assertEquals(3,newMap.size());
	}
	
	@Test
	public void testGetDescription() {
		System.out.println("Description");
		List<String> strings = keyBindings.getDescription();
		
		assertEquals("Cancel\t2", strings.get(0));
		assertEquals("Confirm\t3", strings.get(1));
		assertEquals("Toggle Dialogue\t4", strings.get(2));
		
		for(String str : keyBindings.getDescription()) {
			System.out.println(str);
		}
		System.out.println();
	}
	
	@Test
	public void testToXML() {
		System.out.println("TO XML");
		System.out.println(keyBindings.toXML());
		System.out.println();
	}

}
