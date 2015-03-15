package controllerTest;

import java.awt.Button;
import java.awt.event.KeyEvent;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import controller.InputParser;
import controller.KeyDispatcher;

public class InputParserTest {

	private InputParser parser;
	private KeyDispatcher keyDisp;
	private KeyEvent keyEvent;
	
	@Before
	public void setUp() throws Exception {
		
		keyDisp = EasyMock.createNiceMock(KeyDispatcher.class);
		parser = new InputParser(keyDisp);
		keyEvent = new KeyEvent(
				new Button("click"),
				KeyEvent.KEY_TYPED,
				System.currentTimeMillis(),
				0, KeyEvent.VK_UNDEFINED,
				'Z');
	}

	@Test
	public void testKeyPressed() {
		keyDisp.useKey(keyEvent.getKeyCode());
		EasyMock.expectLastCall();
		
		EasyMock.replay(keyDisp);
		
		parser.keyPressed(keyEvent);
		
		EasyMock.verify(keyDisp);
	}
	
	@Test
	public void testKeyPressedAgain() {
		keyEvent.setKeyCode(6);
		keyDisp.useKey(keyEvent.getKeyCode());
		EasyMock.expectLastCall();
		
		EasyMock.replay(keyDisp);
		
		parser.keyPressed(keyEvent);
		
		EasyMock.verify(keyDisp);
	}

}
