package controllerTest.menu;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import controller.commands.Commandable;
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsOption;
import controller.menu.keyBindings.KeyBindingsMenu;

public class KeyBindingsMenuTest {

	private KeyBindings keyBindings;
	private Commandable command1;
	private Commandable command2;
	private Commandable command3;
	private KeyBindingsMenu menu;
	
	@Before
	public void setUp() throws Exception {
		keyBindings = EasyMock.createNiceMock(KeyBindings.class);
		command1 = EasyMock.createNiceMock(Commandable.class);
		command2 = EasyMock.createNiceMock(Commandable.class);
		command3 = EasyMock.createNiceMock(Commandable.class);
		
		List<KeyBindingsOption> options = new ArrayList<>();
		options.add(KeyBindingsOption.DOWN);
		options.add(KeyBindingsOption.CONFIRM);
		options.add(KeyBindingsOption.RIGHT);
		
		Map<KeyBindingsOption, Commandable> commands = new HashMap<>();
		commands.put(KeyBindingsOption.DOWN, command1);
		commands.put(KeyBindingsOption.CONFIRM, command2);
		commands.put(KeyBindingsOption.RIGHT, command3);
		
		menu = new KeyBindingsMenu(keyBindings, options, KeyBindingsOption.DOWN, commands);
	}

	@Test
	public void testNext() {
		assertEquals(KeyBindingsOption.DOWN, menu.getActiveOption());
		menu.next();
		assertEquals(KeyBindingsOption.CONFIRM, menu.getActiveOption());
	}
	
	@Test
	public void testNextTwice() {
		assertEquals(KeyBindingsOption.DOWN, menu.getActiveOption());
		menu.next();
		assertEquals(KeyBindingsOption.CONFIRM, menu.getActiveOption());
		menu.next();
		assertEquals(KeyBindingsOption.RIGHT, menu.getActiveOption());
	}
	
	@Test
	public void testNextThrice() {
		assertEquals(KeyBindingsOption.DOWN, menu.getActiveOption());
		menu.next();
		assertEquals(KeyBindingsOption.CONFIRM, menu.getActiveOption());
		menu.next();
		assertEquals(KeyBindingsOption.RIGHT, menu.getActiveOption());
		menu.next();
		assertEquals(KeyBindingsOption.DOWN, menu.getActiveOption());
	}
	
	@Test
	public void testPrevious() {
		assertEquals(KeyBindingsOption.DOWN, menu.getActiveOption());
		menu.previous();
		assertEquals(KeyBindingsOption.RIGHT, menu.getActiveOption());
	}
	
	@Test
	public void testPreviousTwice() {
		assertEquals(KeyBindingsOption.DOWN, menu.getActiveOption());
		menu.previous();
		assertEquals(KeyBindingsOption.RIGHT, menu.getActiveOption());
		menu.previous();
		assertEquals(KeyBindingsOption.CONFIRM, menu.getActiveOption());
	}
	
	@Test
	public void testPreviousThrice() {
		assertEquals(KeyBindingsOption.DOWN, menu.getActiveOption());
		menu.previous();
		assertEquals(KeyBindingsOption.RIGHT, menu.getActiveOption());
		menu.previous();
		assertEquals(KeyBindingsOption.CONFIRM, menu.getActiveOption());
		menu.previous();
		assertEquals(KeyBindingsOption.DOWN, menu.getActiveOption());
	}
	
	@Test
	public void testTwoNextOnePrevious() {
		assertEquals(KeyBindingsOption.DOWN, menu.getActiveOption());
		menu.next();
		assertEquals(KeyBindingsOption.CONFIRM, menu.getActiveOption());
		menu.next();
		assertEquals(KeyBindingsOption.RIGHT, menu.getActiveOption());
		menu.previous();
		assertEquals(KeyBindingsOption.CONFIRM, menu.getActiveOption());
	}
	
	@Test
	public void testConfirmDown() {
		command1.execute();
		EasyMock.expectLastCall();
		
		EasyMock.replay(command1);
		
		menu.confirm();
		
		EasyMock.verify(command1);
	}
	
	@Test
	public void testConfirmConfirm() {
		command2.execute();
		EasyMock.expectLastCall();
		
		EasyMock.replay(command2);
		menu.next();
		menu.confirm();
		
		EasyMock.verify(command2);
	}
	
	@Test
	public void testConfirmRight() {
		command3.execute();
		EasyMock.expectLastCall();
		
		EasyMock.replay(command3);
		menu.next();
		menu.next();
		menu.confirm();
		
		EasyMock.verify(command3);
	}
	
	@Test
	public void testAllConfirms() {
		command1.execute();
		EasyMock.expectLastCall();
		command2.execute();
		EasyMock.expectLastCall();
		command3.execute();
		EasyMock.expectLastCall();
		
		EasyMock.replay(command1);
		EasyMock.replay(command2);
		EasyMock.replay(command3);
		menu.confirm();
		menu.next();
		menu.confirm();
		menu.next();
		menu.confirm();
		
		EasyMock.verify(command1);
		EasyMock.verify(command2);
		EasyMock.verify(command3);
	}
	
	@Test
	public void testDescription() {
		List<String> testList = new ArrayList<>();
		testList.add("hi");
		testList.add("bye");
		
		EasyMock.expect(keyBindings.getDescription()).andReturn(testList);
		
		EasyMock.replay(keyBindings);
		
		List<String> list = menu.getDescription();
		
		assertEquals(testList,list);
		
		EasyMock.verify(keyBindings);
	}

}
