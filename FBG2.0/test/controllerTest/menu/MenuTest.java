package controllerTest.menu;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import controller.commands.Commandable;
import controller.menu.Menu;
import controller.menu.MenuOption;

public class MenuTest {

	private Menu menu;
	private Commandable command1;
	private Commandable command2;
	private Commandable command3;
	
	@Before
	public void setUp() throws Exception {
		command1 = EasyMock.createNiceMock(Commandable.class);
		command2 = EasyMock.createNiceMock(Commandable.class);
		command3 = EasyMock.createNiceMock(Commandable.class);
		
		List<MenuOption> menuOptions = new ArrayList<>();
		menuOptions.add(MenuOption.NEW_GAME);
		menuOptions.add(MenuOption.RESUME_GAME);
		menuOptions.add(MenuOption.EXIT_GAME);
		
		Map<MenuOption, Commandable> testMap = new HashMap<>();
		testMap.put(MenuOption.NEW_GAME, command1);
		testMap.put(MenuOption.RESUME_GAME, command2);
		testMap.put(MenuOption.EXIT_GAME, command3);
		
		menu = new Menu(menuOptions, MenuOption.NEW_GAME, testMap);
	}

	@Test
	public void testNext() {
		assertEquals(MenuOption.NEW_GAME, menu.getActiveOption());
		menu.next();
		assertEquals(MenuOption.RESUME_GAME, menu.getActiveOption());
	}
	
	@Test
	public void testTwoNexts() {
		assertEquals(MenuOption.NEW_GAME, menu.getActiveOption());
		menu.next();
		assertEquals(MenuOption.RESUME_GAME, menu.getActiveOption());
		menu.next();
		assertEquals(MenuOption.EXIT_GAME, menu.getActiveOption());
	}
	
	@Test
	public void testThreeNexts() {
		assertEquals(MenuOption.NEW_GAME, menu.getActiveOption());
		menu.next();
		assertEquals(MenuOption.RESUME_GAME, menu.getActiveOption());
		menu.next();
		assertEquals(MenuOption.EXIT_GAME, menu.getActiveOption());
		menu.next();
		assertEquals(MenuOption.NEW_GAME, menu.getActiveOption());
	}
	
	@Test
	public void testPrevious() {
		assertEquals(MenuOption.NEW_GAME, menu.getActiveOption());
		menu.previous();
		assertEquals(MenuOption.EXIT_GAME, menu.getActiveOption());
	}
	
	@Test
	public void test3Previous() {
		assertEquals(MenuOption.NEW_GAME, menu.getActiveOption());
		menu.previous();
		assertEquals(MenuOption.EXIT_GAME, menu.getActiveOption());
		menu.previous();
		assertEquals(MenuOption.RESUME_GAME, menu.getActiveOption());
		menu.previous();
		assertEquals(MenuOption.NEW_GAME, menu.getActiveOption());
	}
	
	@Test
	public void test2Next1Previous() {
		assertEquals(MenuOption.NEW_GAME, menu.getActiveOption());
		menu.next();
		assertEquals(MenuOption.RESUME_GAME, menu.getActiveOption());
		menu.next();
		assertEquals(MenuOption.EXIT_GAME, menu.getActiveOption());
		menu.previous();
		assertEquals(MenuOption.RESUME_GAME, menu.getActiveOption());
	}
	
	@Test
	public void testExecuteNewGame() {
		command1.execute();
		EasyMock.expectLastCall();
		
		EasyMock.replay(command1);
		
		assertEquals(MenuOption.NEW_GAME, menu.getActiveOption());
		menu.execute();
		
		EasyMock.verify(command1);
	}
	
	@Test
	public void testExecuteResumeGame() {
		command2.execute();
		EasyMock.expectLastCall();
		
		EasyMock.replay(command2);
		
		menu.next();
		menu.execute();
		
		EasyMock.verify(command2);
	}
	
	@Test
	public void testExecuteExitGame() {
		command3.execute();
		EasyMock.expectLastCall();
		
		EasyMock.replay(command3);
		
		menu.next();
		menu.next();
		
		menu.execute();
		
		EasyMock.verify(command3);
	}
	
	@Test
	public void testAllExecutes() {
		command1.execute();
		EasyMock.expectLastCall();
		command2.execute();
		EasyMock.expectLastCall();
		command3.execute();
		EasyMock.expectLastCall();
		
		EasyMock.replay(command1);
		EasyMock.replay(command2);
		EasyMock.replay(command3);

		menu.execute();
		menu.next();
		menu.execute();
		menu.next();
		menu.execute();
		
		EasyMock.verify(command1);
		EasyMock.verify(command2);
		EasyMock.verify(command3);
	}
	
	@Test
	public void testDescription() {
		
		List<String> desc = menu.getDescription();
		
		assertEquals("New Game",desc.get(0));
		assertEquals("Resume Game",desc.get(1));
		assertEquals("Exit Game",desc.get(2));
		
		for(String str : desc) {
			System.out.println(str);
		}
	}
	

}
